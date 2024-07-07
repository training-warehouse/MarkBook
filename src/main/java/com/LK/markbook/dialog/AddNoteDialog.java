package com.LK.markbook.dialog;

import com.LK.markbook.data.DataCenter;
import com.LK.markbook.data.DataConvert;
import com.LK.markbook.data.NoteData;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class AddNoteDialog extends DialogWrapper {
    protected EditorTextField fTitle;
    protected EditorTextField fMark;

    public AddNoteDialog() {
        super(true);
        setTitle("添加笔记备注");
        init();
    }

    @NotNull
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        fTitle = new EditorTextField("笔记标题");
        fMark = new EditorTextField("笔记内容");
        fMark.setPreferredSize(new Dimension(200, 100));
        panel.add(fTitle, BorderLayout.NORTH);
        panel.add(fMark, BorderLayout.CENTER);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("添加笔记到列表");
        button.addActionListener(e -> {
            String title = fTitle.getText();
            String mark = fMark.getText();

            String fileType = DataCenter.FILE_NAME.substring(DataCenter.FILE_NAME.lastIndexOf('.') + 1);
            NoteData noteData = new NoteData(title, mark, DataCenter.SELECT_TEXT, DataCenter.FILE_NAME, fileType);
            DataCenter.NOTE_LIST.add(noteData);
            DataCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));

            MessageDialogBuilder.yesNo("操作结果", "添加成功").show();
            // 关闭窗口
            AddNoteDialog.this.dispose();
        });
        panel.add(button);
        return panel;
    }
}
