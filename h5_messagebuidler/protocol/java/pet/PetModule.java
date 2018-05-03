package app.game.module.pet;

import app.game.hero.Hero;
import app.game.hero.base.module.BaseHeroModule;
import app.game.module.scene.HeroFightModule;
import app.protobuf.HeroContent.HeroProto.Builder;
import app.protobuf.HeroMajorContent.HeroServerProto;
import app.protobuf.HeroMinorContent.HeroMinorProto;

/**
 *
 * Created time:2018-04-20 20:01:35
 */
public class PetModule extends BaseHeroModule{

    public  PetModule(int moduleId, Hero hero) {
        super(moduleId, hero);
    }

    @Override
    public void onOffline(HeroFightModule hfm, long ctime) {

    }

    @Override
    public void encode4Client(Builder clientBuilder, long ctime) {

    }

    @Override
    public void onOnline(HeroFightModule hfm, long ctime) {

    }

    @Override
    public void encode4Server(HeroServerProto.Builder serverBuilder, long ctime) {

    }

    @Override
    public void decode4Server(HeroServerProto serverProto, long ctime) {

    }

    @Override
    public void encode4Minor(HeroMinorProto.Builder minorBuilder, long ctime) {

    }

    @Override
    public void decode4Minor(HeroMinorProto minorProto, long ctime) {

    }
}
