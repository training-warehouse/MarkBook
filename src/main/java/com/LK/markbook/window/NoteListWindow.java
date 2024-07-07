package com.LK.markbook.window;

import com.LK.markbook.data.DataCenter;
import com.LK.markbook.processor.DefaultSourceNoteData;
import com.LK.markbook.processor.MDFreeMarkProcessor;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteListWindow {
    private JTextField tfTopic;
    private JTable tbContent;
    private JButton btnCreate;
    private JButton btnClear;
    private JButton btnClose;
    private JPanel contentPanel;

    private void init() {
        tbContent.setModel(DataCenter.TABLE_MODEL);
        tbContent.setEnabled(true);
    }

    public NoteListWindow(Project project, ToolWindow toolWindow) {
        init();

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = tfTopic.getText();
                if (topic == null || "".equals(topic)) {
                    MessageDialogBuilder.yesNo("操作结果", "请输入笔记标题！").show();
                    return;
                }

                VirtualFile virtualFile = FileChooser.chooseFile(
                        FileChooserDescriptorFactory.createSingleFolderDescriptor(),
                        project, project.getBaseDir()
                );
                if (virtualFile == null) {
                    return;
                }
                String path = virtualFile.getPath();
                String fileName = topic + ".md";
                String filePath = path + "/" + fileName;

                MDFreeMarkProcessor mdFreeMarkProcessor = new MDFreeMarkProcessor();
                try {
                    mdFreeMarkProcessor.process(new DefaultSourceNoteData(filePath, topic, DataCenter.NOTE_LIST));

                    NotificationGroup notificationGroup = new NotificationGroup("markbook_id", NotificationDisplayType.BALLOON, true);
                    Notification notification = notificationGroup.createNotification("生成文档成功", MessageType.INFO);
                    Notifications.Bus.notify(notification);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataCenter.reset();
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
