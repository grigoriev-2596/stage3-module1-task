package com.mjc.school.menu;

import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.MenuInputException;
import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.model.NewsDTORequest;
import com.mjc.school.service.model.NewsDTOResponse;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class NewsMenu {
    Scanner scanner;

    public NewsMenu(Scanner sc) {
        this.scanner = sc;
    }

    private void printMenu() {
        MenuConstant[] menuItems = MenuConstant.values();
        for (MenuConstant i : menuItems) {
            System.out.println(i.getId() + " - " + i.getName());
        }
    }

    public void runMenu(Controller<NewsDTORequest, NewsDTOResponse> controller) {
        String input = "not defined";
        while (true) {
            printMenu();
            System.out.print("Enter the number of operation \n>>");
            input = scanner.nextLine();
            switch (input) {
                case "0":
                    exit(0);
                    break;
                case "1":
                    createNews(controller);
                    break;
                case "2":
                    getAllNews(controller);
                    break;
                case "3":
                    getNewsById(controller);
                    break;
                case "4":
                    updateNews(controller);
                    break;
                case "5":
                    deleteNews(controller);
                    break;
                default:
                    System.out.println("Command not found");
            }
        }
    }

    private void createNews(Controller<NewsDTORequest, NewsDTOResponse> controller) {
        try {
            String title, content;
            long authorId;
            System.out.println("Operation: " + MenuConstant.CREATE_NEWS.getName());
            System.out.print("Enter news title:\n>>");
            title = scanner.nextLine();
            System.out.print("Enter news content:\n>>");
            content = scanner.nextLine();
            System.out.print("Enter author id:\n>>");
            authorId = readId();
            NewsDTOResponse newsDTOResponse = controller.create(new NewsDTORequest(null, title, content, authorId));
            System.out.println(newsDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllNews(Controller<NewsDTORequest, NewsDTOResponse> controller) {
        try {
            System.out.println("Operation: " + MenuConstant.GET_ALL_NEWS.getName());
            List<NewsDTOResponse> news = controller.getAll();

            for (NewsDTOResponse response : news) {
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void getNewsById(Controller<NewsDTORequest, NewsDTOResponse> controller) {
        try {
            System.out.println("Operation: " + MenuConstant.GET_NEWS_BY_ID.getName());
            long newsId;
            System.out.print("Enter news id:\n>>");
            newsId = readId();
            NewsDTOResponse response = controller.getById(newsId);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateNews(Controller<NewsDTORequest, NewsDTOResponse> controller) {
        try {
            String title, content;
            long authorId, newsId;
            System.out.println("Operation: " + MenuConstant.UPDATE_NEWS.getName());
            System.out.print("Enter news id:\n>>");
            newsId = readId();
            System.out.print("Enter news title:\n>>");
            title = scanner.nextLine();
            System.out.print("Enter news content:\n>>");
            content = scanner.nextLine();
            System.out.print("Enter author id:\n>>");
            authorId = readId();
            NewsDTOResponse newsDTOResponse = controller.update(new NewsDTORequest(newsId, title, content, authorId));
            System.out.println(newsDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteNews(Controller<NewsDTORequest, NewsDTOResponse> controller) {
        try {
            System.out.println("Operation: " + MenuConstant.DELETE_NEWS.getName());
            long newsId;
            System.out.print("Enter news id:\n>>");
            newsId = readId();
            System.out.println(controller.delete(newsId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private long readId() {
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            throw new MenuInputException(ErrorCode.ID_MUST_BE_A_NUMBER.toString());
        }
        long id = scanner.nextLong();
        scanner.nextLine();
        return id;
    }
}
