package com.meghpy.englishlearningandieltspreparation;

public class GrammarItem {
    private String lessonTitle;
    private String englishSentence;
    private String banglaTranslation;

    public GrammarItem(String lessonTitle, String englishSentence, String banglaTranslation) {
        this.lessonTitle = lessonTitle;
        this.englishSentence = englishSentence;
        this.banglaTranslation = banglaTranslation;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getEnglishSentence() {
        return englishSentence;
    }

    public String getBanglaTranslation() {
        return banglaTranslation;
    }
}