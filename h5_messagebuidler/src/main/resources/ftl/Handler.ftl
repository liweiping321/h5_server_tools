package app.game.handler.${moduleName};

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.${moduleName}.${cModuleName}Messages;
import app.game.module.scene.HeroFightModule;
<#list imports as import >
import ${import};
</#list>
/**
 * ${desc}
 * Created by CodeMaker
 * Created time: ${dateStr}
 */
@Handler(code=${cModuleName}Messages.${codeName})
public class ${className} extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
         <#list attrs as attr  >
          ${attr}
         </#list>
         // 请在下面添加你的代码
    }
}
