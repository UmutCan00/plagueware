package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entity.*;


public class Savior extends Player implements Updatable {
    int lastDay = 0;
    int lastVacAmount = 0;

    Building controlBuilding;
    int controlBuildingFund = 0;
    int merchFocus = 0; //0-70

    private static final int CONT = 0, ASYMP = 1, MORT = 2;
    boolean spyInfaltration = false;
    int spyType;
    int spyLevel;

    JPanel fundingPanel;

    public Savior(int initialBudget) {
        super(initialBudget);
        GameData.updateManager.addUpdatable(this);
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

    @Override
    protected SkillTree getSkillTree() {
        SkillTreeNode vac = new SkillTreeNode() {

            @Override
            protected void activateEvent() {
                GameData.hospital.hasVaccine = true;
                
            }

            @Override
            public int getCost() {
                return 5000;
            }

            @Override
            public String toString() {
                return "Find vaccination.(Cost: " + getCost() + ")";
            }
            
        };
        SkillTreeNode setUpControlBuilding = new SkillTreeNode() {

            @Override
            protected void activateEvent() {
                try {
                    controlBuilding = new Building(118, 10) {
                        @Override
                        public void enter(Person p) {
                            super.enter(p);
                            if(new Random().nextInt(100) < merchFocus) {
                                addBudget(8);
                            }
                            else {
                                double addedAwareness = (controlBuildingFund - 200) / 50.0;
                                if(p.awareness + addedAwareness < 0) {
                                    p.awareness = 0;
                                }
                                else if(p.awareness + addedAwareness > 100) {
                                    p.awareness = 100;
                                }
                                else {
                                    p.awareness += addedAwareness;
                                }
                            }

                        }

                        @Override
                        public int getNodeWidth() {
                            return 6;
                        }

                        @Override
                        public int getNodeHeight() {
                            return 6;
                        }

                        @Override
                        public Color getColor() {
                            return Color.PINK;
                        }

                        @Override
                        public int getBuildingType() {
                            return Buildings.ENTERTAINMENT;
                        }
                        
                    };
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }

            @Override
            public int getCost() {
                return 300;
            }

            @Override
            public String toString() {
                return "Set Up Control Building. (Cost: " + getCost() + ")";
            }
            
        };
        setUpControlBuilding.addNextNode(vac);
        SkillTreeNode[] roots = {setUpControlBuilding};
        SkillTreeNode freeVac = new SkillTreeNode() {

            @Override
            protected void activateEvent() {
                GameData.hospital.isVaccineFree = true;
            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public String toString() {
                return "Make vaccination free. (Cost: " + getCost() + ")";
            }
            
        };

        SkillTreeNode paidVac = new SkillTreeNode() {

            @Override
            protected void activateEvent() {
                GameData.hospital.isVaccineFree = false;
                
            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public String toString() {
                return "Make vaccination paid. (Cost: " + getCost() + ")";
            }
            
        };
        freeVac.setMutuallyExclusive(paidVac);
        vac.addNextNode(freeVac);
        vac.addNextNode(paidVac);
        SkillTree tree = new SkillTree(roots);
        return tree;
    }

    @Override
    protected void getPaid() {
        budget += GameData.getPersonAmount() * 3;
        if(!GameData.hospital.isVaccineFree) {
            int currentVacAmount = GameData.getVaccinationAmount();
            budget += (currentVacAmount - lastVacAmount) * 15;
            lastVacAmount = currentVacAmount;
        }
        budgetLabel.setText("Budget: " + budget);

        if(!spyInfaltration && new Random().nextInt(30) == 0) { //spy infaltration
            spyInfaltration = true;
            spyType = new Random().nextInt(3);
            spyLevel = 1;
            skillTree.addRoot(new SkillTreeNode() {

                @Override
                protected void activateEvent() {
                    spyInfaltration = false;
                    spyType = -1;
    
                }

                @Override
                public int getCost() {
                    return 1000;
                }

                @Override
                public String toString() {
                    return "Ban Spies. (Cost: " + getCost() + ")";
                }
                
            });
            setActivatables();
        
        }

        if(spyInfaltration) {
            if(spyType == CONT) {
                GameData.virus.setContagiousness(GameData.virus.getContagiousness() + 1 * spyLevel);
                GameData.virus.setAsymptomaticRate(GameData.virus.getAsymptomaticRate() - 0.5 * spyLevel);
                GameData.virus.setMortality(GameData.virus.getMortality() - 0.5 * spyLevel);
            }
            else if(spyType == MORT) {
                GameData.virus.setContagiousness(GameData.virus.getContagiousness() - 0.5 * spyLevel);
                GameData.virus.setAsymptomaticRate(GameData.virus.getAsymptomaticRate() - 0.5 * spyLevel);
                GameData.virus.setMortality(GameData.virus.getMortality() + 1 * spyLevel);
            }
            else if(spyType == ASYMP) {
                GameData.virus.setContagiousness(GameData.virus.getContagiousness() - 0.5 * spyLevel);
                GameData.virus.setAsymptomaticRate(GameData.virus.getAsymptomaticRate() + 1 * spyLevel);
                GameData.virus.setMortality(GameData.virus.getMortality() - 0.5 * spyLevel);
            }
            if(GameData.virus.getContagiousness() < 5 || GameData.virus.getAsymptomaticRate() < 5 || GameData.virus.getMortality() < 5) {
                spyType = new Random().nextInt(3);
                spyLevel++;
            }
        }

        if(GameData.time.day % 3 == 0) {
        merchFocus = -1;
        controlBuildingFund = 0;
        }
        if(GameData.time.day % 3 == 0 && controlBuilding != null && fundingPanel == null) {
            fundingPanel = new JPanel();
            fundingPanel.setLayout(new GridLayout(3, 2));
            fundingPanel.setBorder(BorderFactory.createTitledBorder("Control Building"));

            

            JSlider fundSlider = new JSlider(0, budget);
            JLabel fundLabel = new JLabel("Fund: " + fundSlider.getValue());
            fundSlider.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    fundLabel.setText("Fund: " + fundSlider.getValue());
                    
                }
                
            });
            fundingPanel.add(fundSlider);
            fundingPanel.add(fundLabel);



            JSlider merchSlider = new JSlider(0, 70);
            JLabel merchLabel = new JLabel("Merch Focus: " + merchSlider.getValue() + "%");
            merchSlider.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    merchLabel.setText("Merch Focus: " + merchSlider.getValue() + "%");
                    
                }
                
            });
            fundingPanel.add(merchSlider);
            fundingPanel.add(merchLabel);
            
            JButton fundButton = new JButton("Fund");
            fundButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    controlBuildingFund = fundSlider.getValue();
                    merchFocus = merchSlider.getValue();
                    skillPanel.remove(fundingPanel);
                    fundingPanel = null;
                    addBudget(-controlBuildingFund);
                    
                }
                
                
            });
            fundingPanel.add(fundButton);

            skillPanel.add(fundingPanel);
            fundingPanel.setMaximumSize(fundingPanel.getMinimumSize());
        }
        
    }
    
}
