package ServiceMathMethod;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    private void createMenu(){
        TreeMap<Integer, String> menu = new TreeMap<Integer, String>();
        menu.put(1, "ax + by = c + k");
        menu.put(2, "ax + b > c");
        menu.put(3, "ax + by = c");
        menu.put(4, "a1x + b1y + c1z = d1\n"+"\ta2x + b2y + c2z = d2\n"+"\ta3x + b3y + c3z = d3");
        menu.put(5, "|ax + b| = c");
        menu.put(6, " (a * x + b) / c = d");
        for(Map.Entry<Integer, String> item: menu.sequencedEntrySet())
        {
            System.out.println(item.getKey()+"  "+ item.getValue());
        }
    }
    public void createOption(){
        try
        {
            while(true)
            {
                Scanner scanner = new Scanner(System.in);
                createMenu();
                System.out.print("Enter option(number): ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch(choice)
                {
                    case 1->{
                        System.out.print("Enter a = ");
                        double a = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b = ");
                        double b = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c = ");
                        double c = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter k = ");
                        double k = Double.parseDouble(scanner.nextLine());
                        print(solveEquationWithParameter(a, b, c, k));
                    }
                    case 2->{
                        System.out.print("Enter a = ");
                        double a = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b = ");
                        double b = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c = ");
                        double c = Double.parseDouble(scanner.nextLine());
                        print(solveInequality(a, b, c));
                    }
                    case 3->{
                        System.out.print("Enter a = ");
                        double a = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b = ");
                        double b = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c = ");
                        double c = Double.parseDouble(scanner.nextLine());
                        print(solveTwoVariableEquation(a, b, c));
                    }
                    case 4->{
                        System.out.print("Enter a1 = ");
                        double a1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b1 = ");
                        double b1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c1 = ");
                        double c1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter d1 = ");
                        double d1 = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter a2 = ");
                        double a2 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b2 = ");
                        double b2 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c2 = ");
                        double c2 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter d2 = ");
                        double d2 = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter a3 = ");
                        double a3 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b3 = ");
                        double b3 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c3 = ");
                        double c3 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter d3 = ");
                        double d3 = Double.parseDouble(scanner.nextLine());
                        print(solveThreeVariableSystem(a1, b1, c1, d1,
                                a2, b2, c2, d2,
                                a3, b3, c3, d3));
                    }
                    case 5->{
                        System.out.print("Enter a = ");
                        double a = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b = ");
                        double b = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c = ");
                        double c = Double.parseDouble(scanner.nextLine());
                        print(solveAbsoluteValueEquation(a, b, c));
                    }
                    case 6->{
                        System.out.print("Enter a = ");
                        double a = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter b = ");
                        double b = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter c = ");
                        double c = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter d = ");
                        double d = Double.parseDouble(scanner.nextLine());
                        String numerator = a+" * x + "+b;
                        print(solveFractionalEquation(numerator, c, d));
                    }
                    default -> {System.out.println("No such option");}
                }
                System.out.print("Let's continue?\n 0-Yes 1-No: ");
                String answer = scanner.nextLine();
                if (answer.equals("1")) System.exit(0);
            }
        }catch (NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
        }

    }


    public TreeMap<String, String> solveEquationWithParameter(double a, double b, double c, double k)
    {
        //ax + by = c + k.
        //ax=c+k-by
        //x=(c+k-by)/a
        //by = c + k - ax.
        //y = (c + k - ax)/b
        TreeMap<String, String> result = new TreeMap<String, String>();
        result.put("Вид рівняння", "\n"+ a+"x + "+b+"y = "+ c +" + "+ k);
        if(b==0 || a==0)
        {
            //  System.out.println("Немає рішень");
            result.put("x=Ø", "y=Ø");
            return result;
        }
        if(a==0 && c==0 && k==0)
        {
            result.put("x=ထ", "y=0");
            return result;
        }
        if (a==0)
        {
            double y = (c+k)/b;

            result.put("x=ထ", "y="+y);
            return result;
        }
        double y=0;
        double x =0;
        y=(c+k)/b;
        y=Math.round(y*100.0)/100.0;
        result.put("x="+x, "y="+y);
        y=0;
        x=(c+k)/a;
        x=Math.round(x*100.0)/100.0;
        result.put("x="+x, "y="+y);
        return result;
    }

    private TreeMap<String, String> solveInequality(double a, double b, double c)
    {
        //ax + b > c
        //ax > c - b
        //a>0 => x > (c - b)/a
        //a<0 => x < (c - b)/a
        TreeMap<String, String> result = new TreeMap<String, String>();
        result.put("Вид нерівності", "\n" +a+ "x + "+ b+ " > "+ c);
        if(a==0)
        {
            return result;
        }
        if(a>0)
        {
            double x = (c-b)/a;
            x=Math.round(x*100.0)/100.0;
            result.put("x ∈", "("+x+";" +" ထ+"+")");
            return result;
        }
        double x = (c-b)/a;
        x=Math.round(x*100.0)/100.0;
        result.put("x ∈", "("+"-ထ"+";" +x+")");
        return result;
    }

    public TreeMap<String, String> solveTwoVariableEquation(double a, double b, double c)
    {
        try
        {
            //ax + by = c
            //by = c-ax
            //by = c-ax
            //y = (c-ax)/b
            //ax=c-by
            //x=(c-by)/a
            TreeMap<String, String> result = new TreeMap<String, String>();
            result.put("Вид рівняння", "\n" +a+ "x + "+ b+ " = "+ c);
            if(b==0)
            {
                //  System.out.println("Немає рішень");
                result.put("x=Ø", "y=Ø");
                return result;
            }
            if(a==0 && c==0)
            {
                //  System.out.println("x=будь-яке число і y=0");
                result.put("x=ထ", "y=0");
                return result;
            }
            if (a==0)
            {
                double y = c/b;
                y=Math.round(y*100.0)/100.0;
                result.put("x=ထ", "y="+y);
                return result;
            }
            double y=0;
            double x =0;
            y=c/b;
            y=Math.round(y*100.0)/100.0;
            result.put("x="+x, "y="+y);
            y=0;
            x=c/a;
            x=Math.round(x*100.0)/100.0;
            result.put("x="+x, "y="+y);
            return result;
        }catch (Exception e)
        {
            System.out.println(e.getMessage() +"\n Введіть число.");
            return null;
        }

    }


    public TreeMap<String, String> solveThreeVariableSystem(double a1, double b1, double c1, double d1,
                                                            double a2, double b2, double c2, double d2,
                                                            double a3, double b3, double c3, double d3)
    {
//        a1x + b1y + c1z = d1
//        a2x + b2y + c2z = d2
//        a3x + b3y + c3z = d3
        TreeMap<String, String> result = new TreeMap<String, String>();
        result.put("Вид рівняння", " \n"+ a1+"x + "+b1+"y + "+c1+"z = "+d1+" \n"+ a2+"x + "+b2+"y + "+c2+"z = "+d2+ "\n"+a3+"x + "+b3+"y + "+c3+"z = "+d3);
        double detA = a1*(b2*c3-b3*c2)-b1*(a2*c3 -a3*c2)+c1*(a2*b3-a3*b2);

        if(detA==0) return result;

        double detAx = d1 * (b2 * c3 - b3 * c2) -
                b1 * (d2 * c3 - d3 * c2) +
                c1 * (d2 * b3 - d3 * b2);

        double detAy = a1 * (d2 * c3 - d3 * c2) -
                d1 * (a2 * c3 - a3 * c2) +
                c1 * (a2 * d3 - a3 * d2);

        double detAz = a1 * (b2 * d3 - b3 * d2) -
                b1 * (a2 * d3 - a3 * d2) +
                d1 * (a2 * b3 - a3 * b2);

        double x = detAx / detA;
        x=Math.round(x*100.0)/100.0;
        double y = detAy / detA;
        y=Math.round(y*100.0)/100.0;
        double z = detAz / detA;
        z=Math.round(z*100.0)/100.0;

        result.put("x=", String.valueOf(x));
        result.put("y=", String.valueOf(y));
        result.put("z=", String.valueOf(z));

        return result;

    }

    public TreeMap<String, String> solveAbsoluteValueEquation(double a, double b, double c)
    {
         //|ax + b| = c
        //if c=0            if c>0
        //ax + b= 0         ax+b=c      ax+b=-c
        //ax= -b            ax=c-b      ax=-c-b
        //x= -b/a           x=(c-b)/a   x=(-c-b)/a
        TreeMap<String, String> result = new TreeMap<String, String>();
        result.put("Вид рівняння", " \n |"+a+"x + "+b+"| = "+c);
        if(c<0 || a==0) return result;
        if(c==0)
        {
            double x = (-1)*b/a;
            x=Math.round(x*100.0)/100.0;
            result.put("x =", String.valueOf(x));
            return result;
        }
        double x1 = (c-b)/a;
        double x2 = ((-1)*c-b)/a;
        x1=Math.round(x1*100.0)/100.0;
        x2=Math.round(x2*100.0)/100.0;
        result.put("x1 =", String.valueOf(x1));
        result.put("x2 =", String.valueOf(x2));
        return result;
    }

    private TreeMap<String, String> solveFractionalEquation(String numerator, double denominator, double constant)
    {
        //(a * x + b) / c = d.
        //a * x + b=d*c
        //a * x=d*c-b
        //x=(d*c-b)/a
        //numerator = a * x + b
        //denominator=c
        //constant=d
        TreeMap<String, String> result = new TreeMap<String, String>();
        result.put("Вид рівняння", " \n"+numerator+" / "+ denominator+" = "+ constant);
        if (denominator==0)return result;
        String regex = "([\\d.-]+)\\s*\\*\\s*x\\s*\\+\\s*([\\d.-]+)";


        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numerator);

        if (matcher.find()) {
            double a = Double.parseDouble(matcher.group(1));
            double b = Double.parseDouble(matcher.group(2));
            double x = (constant*denominator-b)/a;
            x=Math.round(x*100.0)/100.0;
            result.put("x =", String.valueOf(x));
        }
        return result;

    }

    public void print(TreeMap<String, String> result)
    {
        if(result.size()==1)
        {
            var answer = result.firstEntry();
            System.out.println(answer.getKey()+" "+ answer.getValue()+ "\n" +"Неможливо отримати рішення.");
            return;
        }
        if (result==null)
        {
            System.out.println("Виникла помилка.");
            return;
        }

        for (Map.Entry<String, String> item: result.descendingMap().entrySet())
        {
            System.out.println(item.getKey() + " "+ item.getValue()+"\n");
        }

    }


}
