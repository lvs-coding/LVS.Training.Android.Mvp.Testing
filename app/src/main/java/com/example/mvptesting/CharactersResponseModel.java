package com.example.mvptesting;


import java.util.List;

public class CharactersResponseModel {

    public CharactersResponseModel(int count, String next, String previous, List<CharacterModel> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    private int count;
    private String next;
    private String previous;
    private List<CharacterModel> results;
}
