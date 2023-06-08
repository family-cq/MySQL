package util;

import cn.zhshipu.log.WinLog;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

import static config.Config.*;

public class SetIconForJtree extends DefaultTreeCellRenderer {
    WinLog log = new WinLog("SetIconForJtree");
    ImageIcon userIcon = null;
    String str = null;
    JLabel label = new JLabel();

    int w=30,h=30;
    DefaultTreeCellRenderer render;
    private String key;
    public boolean isInt(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
//        log.info("tree = "+tree);

        if (node == null)
            return null;
        String[] nodeArr = node.toString().split(":");
        if (sel){   //是否选中
            label.setOpaque(false);//设置该组件边缘像素不可编辑
            label.setForeground(Color.red);
            label.setBackground(getBackgroundSelectionColor());
        }else {
            label.setOpaque(true);
            label.setBackground(getBackgroundNonSelectionColor());
            label.setForeground(getTextNonSelectionColor());
        }

        switch (node.getLevel()){
            case 0:
                logo.setImage(logo.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
                userIcon = logo;
                break;
            case 1:
                con2_png.setImage(con2_png.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
                userIcon = con2_png;
                break;
            case 2:
                sjk_png.setImage(sjk_png.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
                userIcon = sjk_png;
                break;
            case 3:
                duoBiao_png.setImage(duoBiao_png.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
                userIcon = duoBiao_png;
                break;
            case 4:
                danBiao_png.setImage(danBiao_png.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
                userIcon = danBiao_png;
                break;
            default:
                break;
        }

        str = node.toString();
        label.setIcon(userIcon);
        label.setText(str);
        label.setFont(f_30);
        return label;
    }
}
