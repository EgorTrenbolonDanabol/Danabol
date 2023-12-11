package ru.ssau.lr7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.lr7.functions.*;
import ru.ssau.lr7.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.lr7.functions.factory.TabulatedFunctionFactory;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("tabulatedFunctionForm")
public class TabulatedFunctionController {

    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.GET)
    public String amountOfPointsForm(@RequestParam(name = "error", required = false, defaultValue = "") String error, Model model) {
        TabulatedFunctionForm tabulatedFunctionForm = new TabulatedFunctionForm();
        model.addAttribute("tabulatedFunctionForm", tabulatedFunctionForm);
        return "createTabulatedFunction";
    }

    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.POST)
    public String amountOfPointsSubmit(@ModelAttribute TabulatedFunctionForm tabulatedFunctionForm, Model model) {
        model.addAttribute("tabulatedFunctionForm", tabulatedFunctionForm);
        System.out.println(tabulatedFunctionForm.getAmount());
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
        tabulatedFunctionForm.createTabulatedFunction();
        System.out.println(tabulatedFunctionForm.getAmount());
        return "tabulatedFunctionResult";
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
    @RequestMapping(value = "/tabulatedFunctionMathForm", method = RequestMethod.POST, name="reg")
    public String xFxTPost(@ModelAttribute MathTabulatedFunctionForm mathTabulatedFunctionForm, Model model) {
        model.addAttribute("mathTabulatedFunctionForm", mathTabulatedFunctionForm);
        model.addAttribute("listFunctions", mapa);
        TabulatedFunction arrtab;
        TabulatedFunctionFactory factory=new ArrayTabulatedFunctionFactory();
        arrtab=factory.create(
                mapa.get(mathTabulatedFunctionForm.getFunction()),
                mathTabulatedFunctionForm.getXFrom(),
                mathTabulatedFunctionForm.getXTo(),
                mathTabulatedFunctionForm.getCount());
        System.out.println(mathTabulatedFunctionForm.getXFrom());
        System.out.print(arrtab);
        return "tabulatedFunctionMathForm";
    }
}