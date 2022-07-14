const AC_GAME_OBJECTS=[];

export class AcGameObject{
    constructor() {
        AC_GAME_OBJECTS.push(this);
        //处理速度，记录时间间隔
        this.timedelta=0;
        //判断是否执行过start
        this.has_called_start=false;
    }

    //只执行一次
    start(){

    }

    //每一帧执行一次，在start后执行
    update(){

    }

    //删除之前执行
    on_destroy(){

    }

    //删除
    destroy(){
        this.on_destroy();

        for(let i in AC_GAME_OBJECTS){
            const obj=AC_GAME_OBJECTS[i];
            if(obj===this){
                AC_GAME_OBJECTS.splice(i);
                break;
            }
        }
    }
}

//每秒钟执行60次
let last_timestamp;
const step=(timestamp)=>{
    for(let obj of AC_GAME_OBJECTS){
        if(!obj.has_called_start){
            obj.has_called_start=true;
            obj.start();
        }else{
            obj.timedelta=timestamp-last_timestamp;
            obj.update();
        }
    }

    last_timestamp=timestamp;
    requestAnimationFrame(step)
}

requestAnimationFrame(step)