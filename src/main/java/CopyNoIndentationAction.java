import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ide.CopyPasteManager;

import java.awt.datatransfer.StringSelection;

public class CopyNoIndentationAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("hello");
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        String s = removeIndentation(editor);
        if (s == null) {
            return;
        }
        System.out.println(s);
        CopyPasteManager.getInstance().setContents(new StringSelection(s));
    }
    private String getSelectedText(Editor editor) {
        return editor.getSelectionModel().getSelectedText();
    }

    private String removeIndentation(Editor editor) {
        String s = getSelectedText(editor);
        if (s == null) {
            return null;
        }
        String[] rows = s.split("\n");
        StringBuilder sb = new StringBuilder();
        String t;
        String last = null;
        for (int i = rows.length - 1; i >= 0; i--) {
            if (!rows[i].trim().equals("")) {
                last = rows[i];
                break;
            }
        }
        if (last == null) {
            return null;
        }
        int i = 0;
        while (last.charAt(i) == ' ') {
            i++;
        }

        for (int j = 0; j < rows.length; j++) {
            t = rows[j];
            System.out.println(t);
            if (t == null) {
                continue;
            }
            if (j == 0) {
                sb.append(t.trim());
            } else if (t.length() < i) {
                sb.append(t.trim());
            } else {
                sb.append(t.substring(i));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
