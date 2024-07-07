package com.LK.markbook.data;

public class NoteData {
    private String title;
    private String mark;
    private String content;
    private String filename;
    private String fileType;

    public NoteData(String title, String mark, String content, String filename, String fileType) {
        this.title = title;
        this.mark = mark;
        this.content = content;
        this.filename = filename;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "NoteData{" +
                "title='" + title + '\'' +
                ", mark='" + mark + '\'' +
                ", content='" + content + '\'' +
                ", filename='" + filename + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
