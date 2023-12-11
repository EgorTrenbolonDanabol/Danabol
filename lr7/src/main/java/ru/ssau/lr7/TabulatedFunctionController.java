package ru.ssau.lr7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.lr7.functions.*;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@SessionAttributes({"tabulatedFunctionForm", "settings", "mathTabulatedFunctionForm"})
public class TabulatedFunctionController {
    Settings settings=new Settings("array");
    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.GET)
    public String amountOfPointsForm(@RequestParam(name = "error", required = false, defaultValue = "") String error, Model model) {
        TabulatedFunctionForm tabulatedFunctionForm = new TabulatedFunctionForm();
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
        return "index";
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
    Map<String, MathFunction> mapa=new HashMap<>();
    @RequestMapping(value = "/tabulatedFunctionMathForm", method = RequestMethod.GET)
    public String xFromXto(@RequestParam(name = "error", required = false, defaultValue = "") String error, Model model) {
        MathTabulatedFunctionForm mathTabulatedFunctionForm = new MathTabulatedFunctionForm();
        mapa.put("Identity", new IdentityFunction());
        mapa.put("Sqr", new SqrFunction());
        mapa.put("Uni", new UnitFunction());
        mapa.put("Zero", new ZeroFunction());
        model.addAttribute("mathTabulatedFunctionForm", mathTabulatedFunctionForm);
        model.addAttribute("listFunctions", mapa);
        return "tabulatedFunctionMathForm";
    }
    @RequestMapping(value = "/tabulatedFunctionMathForm", method = RequestMethod.POST, name = "reg")
    public String xFxTPost(@ModelAttribute MathTabulatedFunctionForm mathTabulatedFunctionForm, Model model) {
        model.addAttribute("mathTabulatedFunctionForm", mathTabulatedFunctionForm);
        model.addAttribute("listFunctions", mapa);

        MathFunction selectedFunction = mapa.get(mathTabulatedFunctionForm.getFunction());
        switch (settings.getTabulatedFunctionFactory()) {
            case "array" -> mathTabulatedFunctionForm.createArray(selectedFunction);
            case "linked" -> mathTabulatedFunctionForm.createLinked(selectedFunction);
        };


        System.out.println(mathTabulatedFunctionForm.getTabulatedFunction());
        return "index";
    }

    // начало второго задания(насткройка)

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String SettingsPage(Model model) {
        model.addAttribute("settings", settings);
        return "settings";
    }
    @RequestMapping(value = "/settings", method = RequestMethod.POST, name="set")
    public String set(@ModelAttribute Settings settings, Model model) {
        model.addAttribute("settings", settings);
        //System.out.print(settings.getTabulatedFunctionFactory());
        return "index";
    }


    //калькулятор

    @RequestMapping(value = "/operations", method = RequestMethod.GET) // открытие формы с калькулятором
    public String operationForm(Model model) {
        Operations operations=new Operations();
        model.addAttribute("operations", operations);
        return "operations";
    }




}

