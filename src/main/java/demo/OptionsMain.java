package demo;

import demo.proto.options.Demo;

import static demo.proto.options.Options.fieldOption;
import static demo.proto.options.Options.messageOption;

public class OptionsMain {
    public static void main(String[] args) {
        System.out.println(Demo.getDescriptor().getOptions().getExtension(messageOption)); // "Message option"
        System.out.println(Demo.getDescriptor().findFieldByNumber(1).getOptions().getExtension(fieldOption)); // "Field option"
    }
}
