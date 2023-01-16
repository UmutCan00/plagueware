package entity;

public abstract class SkillTreeNode {
    private SkillTreeNode[] nextNodes;
    private int nextNodeAmount;
    private boolean isActive;
    private boolean isActivatable;
    private SkillTreeNode mutuallyExclusive;

    public SkillTreeNode() {
        nextNodes = new SkillTreeNode[2];
        isActive = false;
        isActivatable = true;
    }
    public void addNextNode(SkillTreeNode node) {
        if(nextNodeAmount == nextNodes.length) {
            SkillTreeNode[] newArr = new SkillTreeNode[nextNodes.length * 2];
            for (int i = 0; i < nextNodes.length; i++) {
                newArr[i] = nextNodes[i];
            }
            nextNodes = newArr;
        }
        nextNodes[nextNodeAmount] = node;
        nextNodeAmount++;
    }
    public SkillTreeNode[] getNextNodes() {
        return nextNodes.clone();
    }
    public boolean isActive() {
        return isActive;
    }
    public boolean isActivatable() {
        return isActivatable;
    }
    public void activate() {
        if(isActivatable) {
            activateEvent();
            isActive = true;
            isActivatable = false;
            if(mutuallyExclusive != null) {
                mutuallyExclusive.isActivatable = false;
            }
        }
    }
    public void setMutuallyExclusive(SkillTreeNode node) {
        this.mutuallyExclusive = node;
        node.mutuallyExclusive = this;
    }
    protected abstract void activateEvent();
    public abstract int getCost();
    public abstract String toString();
}
