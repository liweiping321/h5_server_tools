package app.game.module.scene;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
import java.util.Collection;
/**
* 场景,战斗相关
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class SceneMessages {
    public static final int MODULE_ID = Modules.SCENE_MODULE_ID;


    /**
    *客户端加载完场景数据
    */
    public static final int C2S_SCENE_LOADED=3001;

    /**
    *改变可视范围大小
    */
    public static final int C2S_SCENE_CHANGE_VIEW_RANGE=3003;

    /**
    *请求换线
    */
    public static final int C2S_SCENE_CHANGE_LINE=3005;

    /**
    *请求各条线的负载信息
    */
    public static final int C2S_SCENE_GET_LINE_INFO=3007;

    /**
    *英雄移动
    */
    public static final int C2S_SCENE_HERO_MOVE=3009;

    /**
    *停止移动
    */
    public static final int C2S_SCENE_STOP_MOVE=3011;

    /**
    *改变自己面对的方向
    */
    public static final int C2S_SCENE_CHANGE_DIRECTION=3013;

    /**
    *客户端请求跳跃
    */
    public static final int C2S_SCENE_HERO_JUMP=3015;

    /**
    *请求传送
    */
    public static final int C2S_SCENE_REQUEST_TRANSPORT=3017;

    /**
    *释放技能
    */
    public static final int C2S_SCENE_RELEASE_SPELL=3019;

    /**
    *请求复活
    */
    public static final int C2S_SCENE_REQUEST_RELIVE=3021;

    /**
    *普通场景的瞬移点请求瞬移
    */
    public static final int C2S_SCENE_HERO_TELE_PORT=3023;

    /**
    *查询周围玩家
    */
    public static final int C2S_SCENE_GET_SURROUNDING_HERO=3025;

    /**
    *更改技能目标
    */
    public static final int C2S_SCENE_UPDATE_SPELL_TARGET=3027;

    /**
    *蓄力技能更新
    */
    public static final int C2S_SCENE_SPELL_CHARGE_UPDATE=3029;

    /**
    *开始挂机自动打怪
    */
    public static final int C2S_SCENE_AUTO_COMBAT=3031;

    /**
    *开始挂机自动打怪
    */
    public static final int C2S_SCENE_CANCEL_AUTO_COMBAT=3033;

    /**
    *回城接口
    */
    public static final int C2S_SCENE_RETURN_CITY=3035;

    /**
    *免费传送
    */
    public static final int C2S_SCENE_FREE_TRANSPORT=3037;

    /**
    *打断持续施法技能
    */
    public static final int C2S_SCENE_INTERRUPT_CASTING_SPELL=3039;

    /**
    *获得最近的怪物坐标
    */
    public static final int C2S_SCENE_GET_NEAREST_MONSTER=3041;

    /**
    *获取能否攻击指定目标
    */
    public static final int C2S_SCENE_GET_CAN_ATTACK_STAT=3043;

    /**
    *获取怪物的位置 
    */
    public static final int C2S_SCENE_GET_MONSTER_POS=3045;

    /**
    *角色进入场景
    */
    public static final int S2C_SCENE_ENTER=3002;
    public static ChannelBuffer createEnterBuffer(int info,int x,int y,int life,int maxLife,Collection<Integer> buffInfos) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_SCENE_ENTER);

        BufferUtil.writeVarInt32(buffer,info); //pk模式,线，场景序列
        BufferUtil.writeVarInt32(buffer,x); //X坐标
        BufferUtil.writeVarInt32(buffer,y); //Y坐标
        BufferUtil.writeVarInt32(buffer,life); //当前生命
        BufferUtil.writeVarInt32(buffer,maxLife); //最大生命
        BufferUtil.writeInts(buffer,buffInfos); //buff列表

        return buffer;
    }

    /**
    *切换场景
    */
    public static final int S2C_SCENE_CHANGE_SCENE=3004;
    public static ChannelBuffer createChangeSceneBuffer(int targetSceneId) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_SCENE_CHANGE_SCENE);

        BufferUtil.writeVarInt32(buffer,targetSceneId); //目标场景

        return buffer;
    }

    /**
    *在视野中添加英雄
    */
    public static final int S2C_SCENE_ADD=3006;
    public static ChannelBuffer createAddBuffer(long sceneObjId,int sex,String name,String guildCode,String guildName,int armyId,int cityId,int cityMasterResult,long resources,long resources2,int titleId,int pkValue,long teleportEndTime,long failTime,int level,int x,int y,int life,int maxLife,Collection<Integer> buffInfos) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_SCENE_ADD);

        BufferUtil.writeVarInt64(buffer,sceneObjId); //英雄id
        BufferUtil.writeVarInt32(buffer,sex); //性别
        BufferUtil.writeUTF(buffer,name); //名字
        BufferUtil.writeUTF(buffer,guildCode); //帮派Id
        BufferUtil.writeUTF(buffer,guildName); //帮派名字
        BufferUtil.writeVarInt32(buffer,armyId); //军团Id
        BufferUtil.writeVarInt32(buffer,cityId); //城市Id,没有盟会为0
        BufferUtil.writeVarInt32(buffer,cityMasterResult); //组成数据 职业 = (result and 7)
        BufferUtil.writeVarInt64(buffer,resources); //换装
        BufferUtil.writeVarInt64(buffer,resources2); //换装2
        BufferUtil.writeVarInt32(buffer,titleId); //称号id
        BufferUtil.writeVarInt32(buffer,pkValue); //pk值
        BufferUtil.writeVarInt64(buffer,teleportEndTime); //瞬移结束时间,用它判断是否正在瞬移。如果正在瞬移,延迟一会儿再展示英雄
        BufferUtil.writeVarInt64(buffer,failTime); //最近一次切磋失败时间，用来展示失败动画
        BufferUtil.writeVarInt32(buffer,level); //等级数据
        BufferUtil.writeVarInt32(buffer,x); //X坐标
        BufferUtil.writeVarInt32(buffer,y); //Y坐标
        BufferUtil.writeVarInt32(buffer,life); //当前生命
        BufferUtil.writeVarInt32(buffer,maxLife); //最大生命
        BufferUtil.writeInts(buffer,buffInfos); //buff列表

        return buffer;
    }

}
