package entity;

public class Virus {
    private double contagiousness;
    private double asymptomaticRate;
    private double mortality;

    public Virus(int contagiousness, int asymptomaticRate, int mortality) {
        this.contagiousness = contagiousness;
        this.asymptomaticRate = asymptomaticRate;
        this.mortality = mortality;
    }

    public double getContagiousness() {
        return this.contagiousness;
    }

    public void setContagiousness(double conatgiosness) {
        if(conatgiosness > 100) {
            this.contagiousness = 100;
        }
        else if(conatgiosness < 0) {
            this.contagiousness = 0;
        }
        else {
            this.contagiousness = conatgiosness;
        }
    }

    public double getAsymptomaticRate() {
        return this.asymptomaticRate;
    }

    public void setAsymptomaticRate(double asymptomaticRate) {
        if(asymptomaticRate > 100) {
            this.asymptomaticRate = 100;
        }
        else if( asymptomaticRate < 0) {
            this.asymptomaticRate = 0;
        }
        else {
            this.asymptomaticRate = asymptomaticRate;
        }
    }

    public double getMortality() {
        return this.mortality;
    }

    public void setMortality(double mortality) {
        if(mortality > 100) {
            this.mortality = 100;
        }
        else if(mortality < 0) {
            this.mortality = 0;
        }
        else {
            this.mortality = mortality;
        }
    }
    
    
}
