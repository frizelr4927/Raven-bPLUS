package keystrokesmod.sToNkS.module.modules.movement;

import keystrokesmod.sToNkS.module.Module;
import keystrokesmod.sToNkS.module.ModuleManager;
import keystrokesmod.sToNkS.module.ModuleSettingSlider;
import keystrokesmod.sToNkS.utils.Utils;
import net.minecraft.client.settings.KeyBinding;

public class BHop extends Module {
   public static ModuleSettingSlider a;
   private final double bspd = 0.0025D;

   public BHop() {
      super("Bhop", Module.category.movement, 0);
      this.registerSetting(a = new ModuleSettingSlider("Speed", 2.0D, 1.0D, 15.0D, 0.2D));
   }

   public void update() {
      Module fly = ModuleManager.getModuleByClazz(Fly.class);
      if (fly != null && !fly.isEnabled() && Utils.Player.isMoving() && !mc.thePlayer.isInWater()) {
         KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
         mc.thePlayer.noClip = true;
         if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
         }

         mc.thePlayer.setSprinting(true);
         double spd = 0.0025D * a.getInput();
         double m = (float)(Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ) + spd);
         Utils.Player.bop(m);
      }
   }
}
