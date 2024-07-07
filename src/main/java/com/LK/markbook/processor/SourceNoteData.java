package com.LK.markbook.processor;

import com.LK.markbook.data.NoteData;

import java.util.List;

public interface SourceNoteData {
    public String getFileName();
    public String getTopic();
    public List<NoteData> getNoteList();
}
