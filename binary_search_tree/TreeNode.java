//written by phantomx999

public class TreeNode {

    private int data;	//value inside tree node
    private TreeNode left;	//left branch
    private TreeNode right;	//right branch

	//constructor
    public TreeNode(int d) {
        this.data = d;
        this.left = null;
        this.right = null;
    }
	
	//constructor
    public TreeNode() {
        this.data = 0;
        this.left = null;
        this.right = null;
    }

	//getters
    public int getData() {
        return data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

	//setters
    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

	//go down right branch til end
    public int getRightMostData() {
        if (this.getRight() == null) {
            return this.getData();
        } else {
            return this.getRight().getRightMostData();
        }

    }

	//remove farther right node on the branch
    public TreeNode removeRightMost(){
        if(this.getRight()==null){
            return this.getLeft();
        }
        else{
            this.setRight(this.getRight().removeRightMost());
            return  this;
        }
    }

}