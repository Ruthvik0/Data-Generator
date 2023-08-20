package runner;

import datagenerator.DataGenerator;

public class Runner {
    public static void main(String[] args){
        Demo obj = DataGenerator.generate(Demo.class);
        System.out.println(obj);
    }
}
