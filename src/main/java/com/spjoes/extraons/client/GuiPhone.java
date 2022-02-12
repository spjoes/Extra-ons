package com.spjoes.extraons.client;

import com.spjoes.extraons.Constants;
import com.spjoes.extraons.handlers.ItemHandler;
import de.erdbeerbaerlp.guilib.components.Button;
import de.erdbeerbaerlp.guilib.gui.BetterGuiScreen;
import de.erdbeerbaerlp.guilib.components.Label;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class GuiPhone extends BetterGuiScreen {
	private Label lbl;
	private Label tagline;
	private Button textButton;

	private BlockPos pos;
	private World worldin;

	public GuiPhone(BlockPos pos, World worldin) {
		setPos(pos);
		setWorldin(worldin);
	}

	private static String[] JOKE_LINES = {
			"The best phone you'll ever have",
			"You get it.... Cause SP.... It has that in the name",
			"Why do companies put X in their phone name?",
			"IAmThePerson Approved!",
			"Shouldn't this be abandon?",
			"A phone for the rest of us"
	};
	@Override
	public void buildGui() {
		lbl = new Label("SPhone X", 10, 50);
		tagline = new Label(JOKE_LINES[new Random().nextInt(JOKE_LINES.length)], 10, 70);
		textButton = new Button(10, 100, 90, "Click Me!");
		addComponent(lbl);
		addComponent(tagline);
		addComponent(textButton);


		textButton.setClickListener(() -> {
			this.getWorldin().createExplosion(mc.player, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 10F, true);
			//this.getWorldin().spawnEntity(new EntityTNTPrimed(this.getWorldin(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), mc.player));
		});

	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setWorldin(World worldin) {
		this.worldin = worldin;
	}

	public World getWorldin() {
		return worldin;
	}

	@Override
	public void updateGui() {

	}

	@Override
	public boolean func_73868_f() {
		return false;
	}

	@Override
	public boolean doesEscCloseGui() {
		return true;
	}
}