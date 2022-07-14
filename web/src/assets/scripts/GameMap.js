import {AcGameObject} from "@/assets/scripts/AcGameObject";

export class GameMap extends AcGameObject {
    constructor(ctx,parent) {
        super();

        this.ctx=ctx;
        this.parent=parent;

        //一个单位长度
        this.L=0;

        //行数，列数
        this.rows=13;
        this.cols=13;
    }

    start(){

    }

    update_size(){
        this.L=Math.min(this.parent.clientWidth/this.cols,this.parent.clientHeight/this.rows);
        this.ctx.canvas.width=this.L*this.cols;
        this.ctx.canvas.height=this.L*this.rows;
    }

    update(){
        this.update_size();
        this.render();
    }

    render(){
        const color_even="#aad751",color_odd="#a2d149";
        for(let r=0;r<this.rows;r++){
            for(let c=0;c<this.cols;c++){
                if((r+c)%2==0){
                    this.ctx.fillStyle=color_even;
                }else{
                    this.ctx.fillStyle=color_odd;
                }
                this.ctx.fillRect(c*this.L,r*this.L,this.L,this.L);
            }
        }
    }
}