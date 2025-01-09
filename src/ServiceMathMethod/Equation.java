package ServiceMathMethod;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {
    public void exmapleDictioanary()
    {
        Dictionary<String, Double> example1 = new Hashtable<>();
        example1.put("a",2.0);
        example1.put("b",4.0);
        example1.put("c",8.1);
        example1.put("k",7.5);
        evaluateExpressionWithDictionary("ax + by = c + k", example1);
        System.out.println();
        System.out.println("///-------//-------///");
        System.out.println();
        Dictionary<String, Double> example2 = new Hashtable<>();
        example2.put("a",0.6);
        example2.put("b",-0.6);
        example2.put("c",1.6);
        evaluateExpressionWithDictionary("ax + by = c", example2);
        System.out.println();
        System.out.println("///-------//-------///");
        System.out.println();
        Dictionary<String, Double> example3 = new Hashtable<>();
        example3.put("a1", 4.7);
        example3.put("b1", 3.7);
        example3.put("c1", 2.7);
        example3.put("d1", 1.7);
        example3.put("a2", 4.7);
        example3.put("b2", 3.7);
        example3.put("c2", 2.7);
        example3.put("d2", 1.7);
        example3.put("a3", 4.7);
        example3.put("b3", 3.7);
        example3.put("c3", 2.7);
        example3.put("d3", 1.7);
        evaluateExpressionWithDictionary("a1x + b1y + c1z = d1\n" + "a2x + b2y + c2z = d2\n" + "a3x + b3y + c3z = d3", example3);
        System.out.println();
        System.out.println("///-------//-------///");
        System.out.println();
        Dictionary<String, Double> example4 = new Hashtable<>();
        example4.put("a",0.6);
        example4.put("b",-0.6);
        example4.put("c",1.6);
        evaluateExpressionWithDictionary("|ax + b| = c", example4);
        System.out.println();
        System.out.println("///-------//-------///");
        System.out.println();
        Dictionary<String, Double> example5 = new Hashtable<>();
        example5.put("a", -1.0);
        example5.put("b", 4.0);
        example5.put("c", 6.0);
        solvePolynomialEquation("ax^2+bx+c=0",example5);


    }


    public TreeMap<String, String>  calculateRPN(String expression) //есть ошибки
    {
        TreeMap<String, String> result = new TreeMap<String, String>();
        Stack<String> examples = new Stack<String>();
        String linearRegex = "^([+-]?\\d*\\.?\\d*)\\s*\\*?\\s*x\\s*([+-]\\s*\\d+)?\\s*=\\s*([+-]?\\d+\\.?\\d*)$";
        String quadraticRegex = "^([+-]?\\d*\\.?\\d*)\\s*\\*?\\s*x\\^2\\s*([+-]\\s*\\d*\\.?\\d*)\\s*\\*?\\s*x?\\s*([+-]\\s*\\d+\\.?\\d*)?\\s*=\\s*0$";

        if (expression.isEmpty() || expression==" ")
        {
            result.put("Помилка:","пустий рядок");
            return result;
        }
        Pattern patternL = Pattern.compile(linearRegex);
        Matcher matcherL= patternL.matcher(expression);

        Pattern patternQ = Pattern.compile(quadraticRegex);
        Matcher matcherQ= patternQ.matcher(expression);
        result.put("Вид рівняння", "\n"+expression);

        if (expression.matches(linearRegex)) {
            if (!matcherL.find()) return result;
            if(Double.parseDouble(matcherL.group(1))==0 && Double.parseDouble(matcherL.group(2))!=Double.parseDouble(matcherL.group(3))) return result;
            if(Double.parseDouble(matcherL.group(1))==0 && Double.parseDouble(matcherL.group(2))==Double.parseDouble(matcherL.group(3)))
            {
                result.put("x =", "ထ");
                return result;
            }
            for (int i = 1; i <= matcherL.groupCount(); i++) {
                if(i==1) examples.push("/");
                examples.push(matcherL.group(i));
                if(i==1) examples.push("-");
            }
        } else if (expression.matches(quadraticRegex)) {
            if (!matcherQ.find()) return result;
            //Корни
            for (int i = 0; i < 2; i++) {
                if (i==1)examples.push("_");
                examples.push("/");
                examples.push("*");

                if (matcherQ.group(1)=="") examples.push("1");
                else if (matcherQ.group(1)=="-") examples.push("-1");
                else examples.push(matcherQ.group(1));

                examples.push("2");
                if (i==0) examples.push("+");
                if(i==1)examples.push("-");
                examples.push("D");
                examples.push("*");

                if (matcherQ.group(2)=="") examples.push("1");
                else if (matcherQ.group(2)=="-") examples.push("-1");
                else examples.push(matcherQ.group(2));
                examples.push("-1");

            }
            //Дискрименант
            examples.push("_");
            examples.push("√");
            examples.push("-");
            examples.push("*");
            examples.push(matcherQ.group(3));
            examples.push("*");
            if (matcherQ.group(1)=="") examples.push("1");
            else if (matcherQ.group(1)=="-") examples.push("-1");
            else examples.push(matcherQ.group(1));
            examples.push("4");
            examples.push("2^");
            if (matcherQ.group(2)=="") examples.push("1");
            else if (matcherQ.group(2)=="-") examples.push("-1");
            else examples.push(matcherQ.group(2));
        }
        String symbol = "";
        double a =0;
        double b = 0;
        double byffer =0;
        double D = 0.0001;
        double x=0;
        while (examples.size()!=0)
        {
            symbol=examples.pop();
            switch (symbol)
            {
                case "+"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer; byffer=0;}
                    a=a+b;
                    b=0;
                }
                case "-"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer;byffer=0;}
                    a=a-b;
                    b=0;
                }
                case "*"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer;byffer=0;}
                    a=a*b;
                    b=0;
                }
                case "/"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer;byffer=0;}
                    a=a/b;
                    b=0;
                }
                case "√"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer;byffer=0;}
                    if(a<0) return result;
                    a=Math.sqrt(a);
                }
                case "2^"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer;byffer=0;}
                    a=Math.pow(a, 2);
                }
                case "D"->{
                    if (b==0 && byffer!=0) { b=a; a=byffer;byffer=0;}
                    if(b==0) b=D;
                }
                case "_"->{
                    if(D==0.0001) {
                        D=a; a=0; b=0;
                    }
                    else if(D==0)
                    {
                        result.put("x = ", String.valueOf(a));
                        return result;
                    }
                    if(x!=0)
                    {
                        a= Math.round(a*100.0)/100.0;
                        result.put("x1 =", String.valueOf(x));
                        result.put("x2 =", String.valueOf(a));
                        return result;
                    }
                    a= Math.round(a*100.0)/100.0;
                    x=a;
                }
                default ->
                {
                    if(byffer==0 && a!=0 && b!=0)
                    {
                        byffer=a;
                        a=Double.parseDouble(symbol);
                    }
                    if(a==0) a=Double.parseDouble(symbol);
                    else if(b==0) b=Double.parseDouble(symbol);

                }
            }
        }
        if (a!=0 && b==0)
        {
            a= Math.round(a*100.0)/100.0;
            result.put("x = ", String.valueOf(a));
        }
        return result;
    }

    public void findMinimumMaximumInExpression(String expression)
    {
        Stack<String> examples = new Stack<String>();
        String[] symbol = errorMathExpression1(expression);

        System.out.println("Вираз: ");
        for (String string : symbol) {
            System.out.print(string);
        }
        System.out.println();

        examples=createSymbolRPN(symbol);
        System.out.println("Порядок RPN: ");
        for (String item:examples)
        {
            System.out.print(item);
        }
        System.out.println();

        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;

        //Нужна буферная переменная (третья)
        double a =0;
        double b=0;
        while (examples.size()>0)
        {
            switch (examples.lastElement())
            {
                case "+"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    a=a+b;
                    b=0;
                    if(a<min) min=a;
                    if(a>max) max=a;
                }
                case "-"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    a=a-b;
                    b=0;
                    if(a<min) min=a;
                    if(a>max) max=a;
                }
                case "*"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    a=a*b;
                    b=0;
                    if(a<min) min=a;
                    if(a>max) max=a;
                }
                case "/"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.firstElement());
                        examples.removeFirst();
                    }
                    if (b==0) System.out.println("Ділити на нуль НЕ можна.");
                    a=a/b;
                    b=0;
                    if(a<min) min=a;
                    if(a>max) max=a;
                }
            }
            examples.removeLast();
        }
        System.out.println("Значення виразу: "+a);
        System.out.println("Максимальне значення: "+max+"\n"+"Мінімальне значення: "+min);


    }

    public void evaluateExpressionWithList(String expression)
    {

        String[] symbol = errorMathExpression1(expression);

        System.out.println("Вираз: ");
        for (String string : symbol) {
            System.out.print(string);
        }
        System.out.println();

        LinkedList<String> examples = new LinkedList<>(createSymbolRPN(symbol));
        System.out.println("Порядок RPN: ");
        for (String item:examples)
        {
            System.out.print(item);
        }
        System.out.println();

        //Нужна буферная переменная (третья)
        double a=0, b=0;
        while (!examples.isEmpty())
        {
            switch (examples.peekLast())
            {
                case "+"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    a=a+b;
                    b=0;
                }
                case "-"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    a=a-b;
                    b=0;
                }
                case "*"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    a=a*b;
                    b=0;
                }
                case "/"->
                {
                    if(a==0)
                    {
                        a=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    if(b==0)
                    {
                        b=Double.parseDouble(examples.peek());
                        examples.removeFirst();
                    }
                    if (b==0) System.out.println("Ділити на нуль НЕ можна.");
                    a=a/b;
                    b=0;
                }
            }
            examples.removeLast();
        }
        System.out.println("Значення виразу: "+a);
    }

    //Полином - P(x)=a0+x*(a1+x*(a2+...x*(an_1+x*an)...))
    public void findRootsInList(String expression, double x)
    {
        String[] symbols = errorMathExpression2(expression);

        LinkedList<String> coefficients = new LinkedList<String>();
        // получаем коэффициенты
        for (int i = 0; i < symbols.length; i++) {
            if(checkSenioritySign(symbols[i])==-1)
            {
                if (i!=0 && Objects.equals(symbols[i - 1], "-")) coefficients.add("-"+symbols[i]);
                else coefficients.add(symbols[i]);
            }
            if (Objects.equals(symbols[i], "^"))++i;

        }
        System.out.println("Size="+coefficients.size());

        double result =Double.parseDouble(Objects.requireNonNull(coefficients.pollFirst()));
        //считаем полином
        for (String item: coefficients)
        {
            result=result*x+Double.parseDouble(item);
        }
        for (String item: symbols)
        {
            System.out.print(item);
        }
        System.out.println();
        System.out.println("x = "+x+"\n"+"Результат P("+x+")="+result);


    }

    private void evaluateExpressionWithDictionary(String expression, Dictionary<String, Double> values)
    {
        Solution solution = new Solution();
        switch (expression)
        {
            case "ax + by = c + k"->
            {
                solution.print(solution.solveEquationWithParameter(values.get("a"), values.get("b"), values.get("c"), values.get("k")));
            }
            case "ax + by = c"->
            {
                solution.print(solution.solveTwoVariableEquation(values.get("a"), values.get("b"), values.get("c")));
            }
            case "a1x + b1y + c1z = d1\n" + "a2x + b2y + c2z = d2\n" + "a3x + b3y + c3z = d3"->
            {
                solution.print(solution.solveThreeVariableSystem(values.get("a1"), values.get("b1"), values.get("c1"), values.get("d1"),
                        values.get("a2"), values.get("b2"), values.get("c2"), values.get("d2"),
                        values.get("a3"), values.get("b3"), values.get("c3"), values.get("d3")));
            }
            case "|ax + b| = c"->
            {
                solution.print(solution.solveAbsoluteValueEquation(values.get("a"), values.get("b"), values.get("c")));
            }
            default -> {System.out.println("Відсутній вибраний варіант.");}
        }
    }

    public void solvePolynomialEquation(String expression, Dictionary<String, Double> coefficients)
    {
        if(expression.isEmpty() || expression.equals(" ")) System.out.println("Передали порожній шаблон рівняння.");
        if(!expression.equals("ax^2+bx+c=0")) System.out.println("Такий варіант не розраховується в даній програмі.");

        System.out.println(expression);
        // проверка дискрименанта
        coefficients.put("D", Math.pow(coefficients.get("b"), 2)- 4 * coefficients.get("a")* coefficients.get("c"));
        if(coefficients.get("D")<0) System.out.println("Розв'язки відсутні.");
        if(coefficients.get("D")==0)
        {
            coefficients.put("x", routeX((-1*coefficients.get("b"))/(2*coefficients.get("a"))));
            System.out.println("x="+coefficients.get("x"));
        }
        else {
            coefficients.put("x1",routeX((-1*coefficients.get("b")+Math.sqrt(coefficients.get("D")))/(2*coefficients.get("a"))));
            coefficients.put("x2",routeX((-1*coefficients.get("b")-Math.sqrt(coefficients.get("D")))/(2*coefficients.get("a"))));
            System.out.println("x1="+coefficients.get("x1"));
            System.out.println("x2="+coefficients.get("x2"));
        }

    }



    //------------------------------------------------------------------------//
    private boolean checkCorrectionNumber(String symbol)
    {
        try{
            Double.parseDouble(symbol);
            return true;
        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private  boolean checkCorrectionBrackets(String[] symbols)
    {
        if (symbols==null) System.out.println("Передаю для перевірки дужок null");
        int size=0;
        for (int i = 0; i < Objects.requireNonNull(symbols).length; i++) {
            if(Objects.equals(symbols[i], "(") && size%2==0) size++;
            if(Objects.equals(symbols[i], ")") && size%2==1) size++;

        }
        if (size%2==0) return true;
        else return false;
    }

    private Stack<String> createSymbolRPN(String[] symbols)
    {
        if(symbols==null) System.out.println("Передаю порожній масив у функцію createSymbolRPN");
        Stack<String> symbolRPN = new Stack<String>();

        //для действий вне скобок
        ArrayList<String> arraySignsOut = new ArrayList<String>();

        //для действий в скобках
        ArrayList<String> arraySignsIn = new ArrayList<String>();
        //для чисел
        ArrayList<String> arrayNumbers= new ArrayList<String>();

        int start = 200;
        // Выписали все знаки в скобках
        assert symbols != null;
        for (int i = 0; i < symbols.length; i++) {
            if(symbols[i]==null) System.out.println("Порожнє значення у = "+ i);
            if (checkSenioritySign(symbols[i])==3)
            {
                start=i;
            }
            else if (checkSenioritySign(symbols[i])==4)
            {
                symbolRPN.addAll(arithmeticOrder(arraySignsIn));
                arraySignsIn=new ArrayList<String>();
                start=200;
            }
            else if (start<i && checkSenioritySign(symbols[i])!=-1){
                arraySignsIn.add(symbols[i]);
            }
            else if (start==200 && checkSenioritySign(symbols[i])!=-1)
            {
                arraySignsOut.add(symbols[i]);
            }
        }

        // запись у стэк
        //за скобками
        symbolRPN.addAll(arithmeticOrder(arraySignsOut));
        Collections.reverse(symbolRPN);

        //числа !!Ошибка в записи чисел для соответсвующих знаков!!
        for (String item: symbolRPN)
        {
            for (int i =0 ; i <symbols.length-1 ; i++) {
                if(symbols[i]==null) System.out.println("Порожнє значення у = "+ i);
                if (Objects.equals(item, symbols[i])&& (Objects.equals(symbols[i], "-")||Objects.equals(symbols[i], "/")))
                {
                    if (!Objects.equals(symbols[i + 1], "") && checkSenioritySign(symbols[i+1])==-1)
                    {
                        arrayNumbers.add(symbols[i+1]);
                        symbols[i+1]="";
                    }
                    if (!Objects.equals(symbols[i - 1], "") && checkSenioritySign(symbols[i-1])==-1)
                    {
                        arrayNumbers.add(symbols[i-1]);
                        symbols[i-1]="";
                    }
                    symbols[i]="";
                }
                else if (Objects.equals(item, symbols[i]))
                {
                    if (!Objects.equals(symbols[i - 1], "") && checkSenioritySign(symbols[i-1])==-1)
                    {
                        arrayNumbers.add(symbols[i-1]);
                        symbols[i-1]="";
                    }
                    if (!Objects.equals(symbols[i + 1], "") && checkSenioritySign(symbols[i+1])==-1)
                    {
                        arrayNumbers.add(symbols[i+1]);
                        symbols[i+1]="";
                    }
                    symbols[i]="";

                }
            }
        }
        for (String arrayNumber : arrayNumbers) {
            symbolRPN.addFirst(arrayNumber);
        }
        return  symbolRPN;
    }

    private int checkSenioritySign(String sign)
    {
        if (sign==null) System.out.println("Передаю порожній рядок для перевірки рангу дії");
        switch (sign)
        {
            case ")"->{return 4;}
            case "("->{return 3;}
            case "*", "/"->{return 2;}
            case "+", "-"->{return 1;}
            case "x", "^"->{return 0;}
            default -> {return -1;}
        }
    }

    private ArrayList<String> arithmeticOrder(ArrayList<String> arraySigns)
    {
        if (arraySigns==null) System.out.println("Передаю null для упорядкування дій");
        if(arraySigns.size()==1) return arraySigns;
        String tempIndex="";
        // Ставим знаки в порядке выполнения
        for (int i = 0; i < arraySigns.size(); i++) {
            for (int j = 0; j < arraySigns.size(); j++) {
                //if(arraySigns[i]==null ) System.out.println("Порожнє значення у i = "+ i );
                //if(arraySigns[j]==null) System.out.println("Порожнє значення у j =  "+ j + "i ="+i+ "=> i ="+arraySigns[i]);

                if (checkSenioritySign(arraySigns.get(i))==checkSenioritySign(arraySigns.get(j))
                        && checkSenioritySign(arraySigns.get(i))<checkSenioritySign(arraySigns.get(j))) continue;
                if (checkSenioritySign(arraySigns.get(i))>checkSenioritySign(arraySigns.get(j)))
                {
                    tempIndex=arraySigns.get(i);
                    arraySigns.set(i, arraySigns.get(j));
                    arraySigns.set(j, tempIndex);
                }

            }
        }
        return arraySigns;
    }

    private String[]  errorMathExpression1(String expression)
    {
        //максимальное количество допустимых элементов
        int maxSize = 40;
        if (expression.isEmpty() || expression==" ")
        {
            System.out.println("Помилка! Рядок порожній. ");
        }
        String[] symbol = expression.split(" ");
        if (symbol.length>maxSize)System.out.println("Перевищує допустимий розмір виразу.");

        //Проверка на наличие чисел
        for (String s : symbol) {
            if (Objects.equals(s, "+") || Objects.equals(s, "-")
                    || Objects.equals(s, "*") || Objects.equals(s, "/")
                    || Objects.equals(s, "(") || Objects.equals(s, ")")) continue;
            else if (!checkCorrectionNumber(s)) {
                System.out.println("Некоректний запис!");
            }
        }

        //Проверка на правильность скобок
        if (!checkCorrectionBrackets(symbol)) System.out.println("Некоректний запис! Не правильно розтавили дужки");
        return symbol;

    }

    private  String[] errorMathExpression2(String expression){
        if (expression.isEmpty() || expression==" ")
        {
            System.out.println("Помилка! Рядок порожній. ");
        }
        String[] symbols = expression.split(" ");
        return symbols;
    }

    private double routeX(double x)
    {
       double result =  Math.round(x*100.0)/100.0;
       return result;
    }

}
