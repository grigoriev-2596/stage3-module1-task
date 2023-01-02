package com.mjc.school;

import com.mjc.school.controller.factory.ControllerFactory;
import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.menu.NewsMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NewsMenu menu = new NewsMenu(new Scanner(System.in));
        NewsController controller = ControllerFactory.getInstance().getNewsController();
        menu.runMenu(controller);
    }
}
