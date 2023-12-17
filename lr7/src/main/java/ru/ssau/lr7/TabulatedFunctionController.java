package ru.ssau.lr7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.lr7.functions.*;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;
import ru.ssau.lr7.operations.DifferentialOperator;
import ru.ssau.lr7.operations.TabulatedDifferentialOperator;
import ru.ssau.lr7.operations.TabulatedFunctionOperationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@SessionAttributes({"tabulatedFunctionForm", "settings", "mathTabulatedFunctionForm", "operations", "differentialOperatorForm"})
public class TabulatedFunctionController {
    Settings settings = new Settings("array");

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String setsession(Model model) {
        TabulatedFunctionForm tabulatedFunctionForm = new TabulatedFunctionForm();
        MathTabulatedFunctionForm mathTabulatedFunctionForm = new MathTabulatedFunctionForm();
        //System.out.print("HELOOOO");
        model.addAttribute("tabulatedFunctionForm", tabulatedFunctionForm);
        model.addAttribute("mathTabulatedFunctionForm", mathTabulatedFunctionForm);
        return "main";
    }

    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.GET)
    public String amountOfPointsForm(@RequestParam(name = "error", required = false, defaultValue = "") String error,
                                     Model model,@ModelAttribute TabulatedFunctionForm tabulatedFunctionForm) {

        model.addAttribute("tabulatedFunctionForm", tabulatedFunctionForm);
        return "createTabulatedFunction";
    }

    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.POST)
    public String amountOfPointsSubmit(@ModelAttribute TabulatedFunctionForm tabulatedFunctionForm, Model model) {
        model.addAttribute("tabulatedFunctionForm", tabulatedFunctionForm);

        //System.out.println(tabulatedFunctionForm.getAmount());
        return "tabulatedFunctionXY";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute TabulatedFunctionForm tabulatedFunctionForm, Model model) {
        // Проверка на сортировку x и y
        if (!isSorted(tabulatedFunctionForm.getXValues()) || !isSorted(tabulatedFunctionForm.getYValues())) {
            model.addAttribute("errorMessage", "X and Y values must be sorted.");
            return "tabulatedFunctionXY";
        }

        model.addAttribute("tabulatedFunctionForm", tabulatedFunctionForm);
        switch (settings.getTabulatedFunctionFactory()) {
            case ("array") -> tabulatedFunctionForm.createArrayTabulatedFunction();
            case ("linked") -> tabulatedFunctionForm.createLinkedListTabulatedFunction();
        }

        System.out.println(tabulatedFunctionForm.getFunction());
        return "main";
    }


    private boolean isSorted(double[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //Вторая половина Первого задания
    ;
    @RequestMapping(value = "/tabulatedFunctionMathForm", method = RequestMethod.GET)
    public String xFromXto(@RequestParam(name = "error", required = false, defaultValue = "") String error, Model model,
                           @ModelAttribute MathTabulatedFunctionForm mathTabulatedFunctionForm) {

        mathTabulatedFunctionForm.getMapa().put("Identity", new IdentityFunction());
        mathTabulatedFunctionForm.getMapa().put("Sqr", new SqrFunction());
        mathTabulatedFunctionForm.getMapa().put("Uni", new UnitFunction());
        mathTabulatedFunctionForm.getMapa().put("Zero", new ZeroFunction());
        model.addAttribute("listFunctions", mathTabulatedFunctionForm.getMapa());
        //System.out.print("YOOOO");
        return "tabulatedFunctionMathForm";
    }

    @RequestMapping(value = "/tabulatedFunctionMathForm", method = RequestMethod.POST, name = "reg")
    public String xFxTPost(@ModelAttribute MathTabulatedFunctionForm mathTabulatedFunctionForm, Model model) {
        MathFunction selectedFunction = mathTabulatedFunctionForm.getMapa().get(mathTabulatedFunctionForm.getFunction());
        switch (settings.getTabulatedFunctionFactory()) {
            case "array" -> mathTabulatedFunctionForm.createArray(selectedFunction);
            case "linked" -> mathTabulatedFunctionForm.createLinked(selectedFunction);
        };


        System.out.println(mathTabulatedFunctionForm.getTabulatedFunction());
        return "main";
    }

    // начало второго задания(насткройка)

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String SettingsPage(Model model) {
        model.addAttribute("settings", settings);
        return "settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST, name = "set")
    public String set(@ModelAttribute Settings settings, Model model) {
        model.addAttribute("settings", settings);
        //System.out.print(settings.getTabulatedFunctionFactory());
        return "main";
    }


    //калькулятор
    Operations operations = new Operations();

    @RequestMapping(value = "/operations", method = RequestMethod.GET) // открытие формы с калькулятором
    public String operationForm(Model model,
                                @ModelAttribute("tabulatedFunctionForm") TabulatedFunctionForm tabulatedFunctionForm,
                                @ModelAttribute("mathTabulatedFunctionForm") MathTabulatedFunctionForm mathTabulatedFunctionForm) {
        if(operations.getType()!=null) {
            TabulatedFunction linkedOperand=tabulatedFunctionForm.getFunction();
            TabulatedFunction mathOperand=mathTabulatedFunctionForm.getTabulatedFunction();

            switch (operations.getType()) {
                case "createTabulatedFunctionOperand1" -> operations.setOperand1(linkedOperand);
                case "createTabulatedFunctionOperand2" -> operations.setOperand2(linkedOperand);
                case "createMathTabulatedFunctionOperand1" -> operations.setOperand1(mathOperand);
                case "createMathTabulatedFunctionOperand2" -> operations.setOperand2(mathOperand);
                default -> {
                }
            }
        }

        model.addAttribute("operations", operations);
        model.addAttribute("settings", settings);
        return "operations";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.POST, params = "createTabulatedFunctionOperand1")
    public String CreateTabulatedFunctionOperand1(Model model) {
        operations.setType("createTabulatedFunctionOperand1");
        model.addAttribute("operations", operations);
        return "/createTabulatedFunction";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.POST, params = "createTabulatedFunctionOperand2")
    public String CreateTabulatedFunctionOperand2(Model model) {
        operations.setType("createTabulatedFunctionOperand2");
        model.addAttribute("operations", operations);
        return "/createTabulatedFunction";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.POST, params = "createMathTabulatedFunctionOperand1")
    public String CreateMathTabulatedFunctionOperand1(Model model) {
        operations.setType("createMathTabulatedFunctionOperand1");
        model.addAttribute("operations", operations);
        return "redirect:/tabulatedFunctionMathForm";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.POST, params = "createMathTabulatedFunctionOperand2")
    public String CreateMathTabulatedFunctionOperand2(Model model) {
        operations.setType("createMathTabulatedFunctionOperand2");
        model.addAttribute("operations", operations);
        return "redirect:/tabulatedFunctionMathForm";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.POST, params = "perform")
    public String performOperation(Model model, @ModelAttribute Settings settings) {
        operations.setSetting(settings.getTabulatedFunctionFactory());

        TabulatedFunction operand1=operations.getOperand1();
        TabulatedFunction operand2=operations.getOperand2();
        TabulatedFunctionOperationService perform = operations.getCombinedTabulatedFunction();
        switch (settings.getOperation()) {
            case "add" -> {
                operations.setResult(perform.Addition(operand1, operand2));
                //System.out.print(perform.Addition(operand1, operand2));
            }
            case "subtract" -> {
                operations.setResult(perform.Subtraction(operand1, operand2));
                //System.out.print(perform.Subtraction(operand1, operand2));
            }
            case "multiply" -> {
                operations.setResult(perform.Multiplication(operand1, operand2));
                //System.out.print(perform.Multiplication(operand1, operand2));
            }
            case "divide" -> {
                operations.setResult(perform.Division(operand1, operand2));
                //System.out.print(perform.Division(operand1, operand2));
            }
            default -> {
            }

        }

        System.out.print(operations.getResult());

        return "/operations";
    }

    //Дифференциальный оператор
    DifferentialOperatorForm differentialOperatorForm=new DifferentialOperatorForm();
    @RequestMapping(value = "/differentialOperator", method = RequestMethod.GET)
    public String DiffPage(Model model, @ModelAttribute("tabulatedFunctionForm") TabulatedFunctionForm tabulatedFunctionForm,
                           @ModelAttribute("mathTabulatedFunctionForm") MathTabulatedFunctionForm mathTabulatedFunctionForm) {

        TabulatedFunction linkedOperand=tabulatedFunctionForm.getFunction();
        TabulatedFunction mathOperand=mathTabulatedFunctionForm.getTabulatedFunction();

        if(differentialOperatorForm.getType()!=null){
            if (Objects.equals(differentialOperatorForm.getType(), "createTabulatedFunction"))
                differentialOperatorForm.setFunction(linkedOperand);
            else if (Objects.equals(differentialOperatorForm.getType(), "createMathTabulatedFunction")) {
                differentialOperatorForm.setFunction(mathOperand);
            }
        }

        model.addAttribute("differentialOperatorForm", differentialOperatorForm);
        return "/differentialOperator";
    }

    @RequestMapping(value = "/differentialOperator", method = RequestMethod.POST, params = "createTabulatedFunction")
    public String CreateTabulatedFunction(Model model) {
        differentialOperatorForm.setType("createTabulatedFunction");
        model.addAttribute("differentialOperatorForm", differentialOperatorForm);
        return "/createTabulatedFunction";
    }
    @RequestMapping(value = "/differentialOperator", method = RequestMethod.POST, params = "createMathTabulatedFunction")
    public String CreateMathTabulatedFunction(Model model) {
        differentialOperatorForm.setType("createMathTabulatedFunction");
        model.addAttribute("differentialOperatorForm", differentialOperatorForm);
        return "redirect:/tabulatedFunctionMathForm";
    }

    @RequestMapping(value = "/differentialOperator", method = RequestMethod.POST, params = "performDiff")
    public String Diffop(Model model) {

        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        switch (settings.getTabulatedFunctionFactory()) {
            case "array" -> tabulatedDifferentialOperator=new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
            case "linked" -> tabulatedDifferentialOperator=new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        }
        differentialOperatorForm.setResult(tabulatedDifferentialOperator.derive(differentialOperatorForm.getFunction()));
        System.out.print(differentialOperatorForm.getResult());
        return "/differentialOperator";
    }


}

