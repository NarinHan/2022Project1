package com.handong.word;

import java.util.Scanner;

public class WordManager {
    Scanner s = new Scanner(System.in);
    WordCRUD wordCRUD;

    public WordManager() {
        wordCRUD = new WordCRUD(s);
    }

    public int selectMenu() {
        System.out.print("*** 영단어 마스터 ***\n"
                + "*****************\n"
                + "1. 모든 단어 보기\n"
                + "2. 수준별 단어 보기\n"
                + "3. 단어 검색\n"
                + "4. 단어 추가\n"
                + "5. 단어 수정\n"
                + "6. 단어 삭제\n"
                + "7. 파일 저장\n"
                + "0. 나가기\n"
                + "*****************\n"
                + "=> 원하는 메뉴는? ");

        return s.nextInt();
    }

    public void start() {
        wordCRUD.loadFile();
        while(true) {
            int menu = selectMenu();
            if(menu == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else if(menu == 1) { // list
                wordCRUD.listAll();
            }
            else if(menu == 2) { // search by level
                wordCRUD.searchLevel();
            }
            else if(menu == 3) { // search by word
                wordCRUD.searchWord();
            }
            else if(menu == 4) { // add
                wordCRUD.addItem();
            }
            else if(menu == 5) { // update
                wordCRUD.updateItem();
            }
            else if(menu == 6) { // delete
                wordCRUD.deleteItem();
            }
            else if(menu == 7) { // save data
                wordCRUD.saveFile();
            }
        }
    }
}
