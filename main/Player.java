package main;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Player {
    protected int budget;
    protected SkillTree skillTree;
    public JPanel skillPanel;
    protected JLabel budgetLabel;
    protected ArrayList<JButton> skills;
    SkillTreeNode[] activatables;

    public Player(int initialBudget) {
        skillPanel = new JPanel();
        skillPanel.setLayout(new BoxLayout(skillPanel, BoxLayout.Y_AXIS));
        this.budget = initialBudget;
        budgetLabel = new JLabel("Budget: " + budget);
        skillPanel.add(budgetLabel);
        skillTree = getSkillTree();
        setActivatables();
        
    }
    protected void setActivatables() {
        if(skills != null) {
            for (JButton skill : skills) {
                skillPanel.remove(skill);
            }
        }
        skills = new ArrayList<JButton>();
        activatables = skillTree.getActivatable();
        for (int i = 0; i < activatables.length; i++) {
            skills.add(new JButton(activatables[i].toString()));
            SkillTreeNode current = activatables[i];

            skills.get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(budget >= current.getCost()) {
                        budget -= current.getCost();
                        budgetLabel.setText("Budget: " + budget);
                        current.activate();
                        setActivatables();
                    }
                    
                }
                
            });
            skillPanel.add(skills.get(i));
        }
    }
    protected void addBudget(int addedBudget) {
        if(budget + addedBudget < 0) {
            budget = 0;
        }
        else {
            budget += addedBudget;
        }
        budgetLabel.setText("Budget: " + budget);
    }
    
    protected abstract SkillTree getSkillTree();
    protected abstract void getPaid();


}
