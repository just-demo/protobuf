package demo;

import self.ed.proto.options.Demo;

import static self.ed.proto.options.Options.fieldOption;
import static self.ed.proto.options.Options.messageOption;

public class OptionsMain {
    public static void main(String[] args) {
        System.out.println(Demo.getDescriptor().getOptions().getExtension(messageOption)); // "Message option"
        System.out.println(Demo.getDescriptor().findFieldByNumber(1).getOptions().getExtension(fieldOption)); // "Field option"
    }
}
