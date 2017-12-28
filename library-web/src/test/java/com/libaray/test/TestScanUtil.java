package com.libaray.test;


import org.framework.beans.util.ScanUtil;

import java.util.List;

public class TestScanUtil {

    public static void main(String[] args) {
        List<String> classNames = ScanUtil.scan("com.library");
        int i= 0;
        for (String className : classNames) {
            System.out.println(className);
            i++;
        }
        System.out.println(i);

    }
}
