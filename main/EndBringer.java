package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import entity.*;
import java.awt.*;

public class EndBringer extends Player implements Updatable{
    private int lastPersonAmount = GameData.getPersonAmount();
    private int lastDay = 0;

    private JPanel changeVirusPanel;

    public EndBringer(int initialBudget) {
        super(initialBudget);
        GameData.updateManager.addUpdatable(this);
        
        
        changeVirusPanel = new JPanel();
        changeVirusPanel.setLayout(new GridLayout(4,1));
        changeVirusPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        ButtonGroup group = new ButtonGroup();
        JRadioButton contagiousnessRadio = new JRadioButton("Contagiousness +5");
        changeVirusPanel.add(contagiousnessRadio);
        JRadioButton mortalityRadio = new JRadioButton("Mortality +5");
        changeVirusPanel.add(mortalityRadio);
        JRadioButton asymptomaticRadio = new JRadioButton("Asymptomatic Rate +5");
        changeVirusPanel.add(asymptomaticRadio);
        group.add(contagiousnessRadio); group.add(mortalityRadio); group.add(asymptomaticRadio);

        int changeVirusCost = 300;
        JButton changeVirusButton = new JButton("Change the Virus (Cost:" + changeVirusCost + ")");
        changeVirusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(budget < changeVirusCost) {
                    return;
                }
                if(contagiousnessRadio.isSelected()) {
                    GameData.virus.setContagiousness(GameData.virus.getContagiousness() + 5.0);
                    GameData.virus.setAsymptomaticRate(GameData.virus.getAsymptomaticRate() - 2.5);
                    GameData.virus.setMortality(GameData.virus.getMortality() - 2.5);
                }
                else if(mortalityRadio.isSelected()) {
                    GameData.virus.setContagiousness(GameData.virus.getContagiousness() - 2.5);
                    GameData.virus.setAsymptomaticRate(GameData.virus.getAsymptomaticRate() - 2.5);
                    GameData.virus.setMortality(GameData.virus.getMortality() + 5);
                }
                else if(asymptomaticRadio.isSelected()) {
                    GameData.virus.setContagiousness(GameData.virus.getContagiousness() - 2.5);
                    GameData.virus.setAsymptomaticRate(GameData.virus.getAsymptomaticRate() + 5);
                    GameData.virus.setMortality(GameData.virus.getMortality() - 2.5);
                }
                else {
                    return;
                }
                budget -= changeVirusCost;
                budgetLabel.setText("Budget: " + budget);
                
            }
            
        });
        changeVirusPanel.add(changeVirusButton);

        skillPanel.add(changeVirusPanel);
        changeVirusPanel.setMaximumSize(changeVirusPanel.getMinimumSize());
        
    }

    @Override
    protected SkillTree getSkillTree() {
        SkillTreeNode[] roots = {
        };
        SkillTree tree = new SkillTree(roots);
        return tree;
    }

    @Override
    protected void getPaid() {
        int currentPersonAmount = GameData.getPersonAmount();
        budget += (lastPersonAmount - currentPersonAmount) * 300;
        budgetLabel.setText("Budget: " + budget);
        lastPersonAmount = currentPersonAmount;

        
        
    }

    @Override
    public void run() {
        if(GameData.time.day != lastDay) {
            getPaid();
            lastDay = GameData.time.day;
        }
        
    }

    @Override
    public boolean hasFullyUpdated() {
        return false;
    }

    @Override
    public void reset() {
        
    }
    
}
