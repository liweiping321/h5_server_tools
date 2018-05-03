package app.game.module.pet;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
/**
* 宠物
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class PetMessages {
    public static final int MODULE_ID = Modules.PET_MODULE_ID;


    /**
    *协议描述(协议头字段有什么用)
    */
    public static final int C2S_PET_REMOVE_OVERLORD=1001;

    /**
    *开启被动技能栏位,p1宠物ID
    */
    public static final int S2C_PET_PETS_OPEN_SKILL_BOX=1002;
    public static ChannelBuffer createPetsOpenSkillBoxBuffer(boolean isGold) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_PET_PETS_OPEN_SKILL_BOX);

        BufferUtil.writeBoolean(buffer,isGold); //是否使用金币

        return buffer;
    }

    /**
    *开启被动技能栏位结果,p1宠物ID
    */
    public static final int C2S_PET_PETS_OPEN_SKILL_BOX1=1005;

    /**
    *放生宠物结果,p1宠物ID
    */
    public static final int S2C_PET_PETS_RELEASE=1006;
    public static ChannelBuffer createPetsReleaseBuffer(boolean success) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_PET_PETS_RELEASE);

        BufferUtil.writeBoolean(buffer,success); //成功失败

        return buffer;
    }

    /**
    *宠物改名,p1宠物ID
    */
    public static final int C2S_PET_PETS_RENAME=1007;

    /**
    *宠物改名结果,p1宠物ID
    */
    public static final int S2C_PET_PETS_RENAME=1008;
    public static ChannelBuffer createPetsRenameBuffer(boolean success) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_PET_PETS_RENAME);

        BufferUtil.writeBoolean(buffer,success); //是否成功

        return buffer;
    }

    /**
    *宠物出战结果,p1宠物ID
    */
    public static final int S2C_PET_PETS_SET_FIGHT=1010;
    public static ChannelBuffer createPetsSetFightBuffer(boolean success) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_PET_PETS_SET_FIGHT);

        BufferUtil.writeBoolean(buffer,success); //是否成功

        return buffer;
    }

    /**
    *使用宠物蛋,p1背包开始位置
    */
    public static final int C2S_PET_PETS_USE_BORN_EGG=1011;

    /**
    *使用宠物技能书,p1宠物ID
    */
    public static final int C2S_PET_PETS_USE_SKILL_BOOK=1013;

    /**
    *使用宠物药水,p1宠物ID
    */
    public static final int S2C_PET_PETS_USE_TRAIN_LIQUID=1014;
    public static ChannelBuffer createPetsUseTrainLiquidBuffer(int templateId) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_PET_PETS_USE_TRAIN_LIQUID);

        BufferUtil.writeVarInt32(buffer,templateId); //药水模版ID

        return buffer;
    }

}
