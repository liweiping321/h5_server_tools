package app.game.handler.goods;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.goods.GoodsMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 使用物品-请求
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=GoodsMessages.C2S_GOODS_USE)
public class GoodsUseHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final long id  =  BufferUtil.readVarInt64(buffer);  //物品唯一ID
          final int count  = BufferUtil.readVarInt32(buffer);  //物品个数
          final int param2  = BufferUtil.readVarInt32(buffer);  //参数1
          final long param3  =  BufferUtil.readVarInt64(buffer);  //参数2
         // 请在下面添加你的代码
    }
}
