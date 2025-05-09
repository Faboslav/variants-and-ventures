package com.faboslav.variantsandventures.common.config.client.gui;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.config.ModMobsConfig;
import com.faboslav.variantsandventures.common.config.VanillaMobsConfig;
import com.faboslav.variantsandventures.common.config.client.gui.widget.DynamicGridWidget;
import com.faboslav.variantsandventures.common.config.client.gui.widget.ImageButtonWidget;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class VariantsAndVenturesConfigScreen extends Screen
{
	private final Screen parent;
	@Nullable
	private Screen modMobsConfigScreen = null;
	@Nullable
	private Screen vanillaMobsConfigScreen = null;

	public VariantsAndVenturesConfigScreen(@Nullable Screen parent) {
		super(Component.translatable("variantsandventures"));
		this.parent = parent;
	}

	@Override
	public void onClose() {
		assert this.minecraft != null;
		this.minecraft.setScreen(this.parent);
	}

	@Override
	public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
		super.renderBackground(context, mouseX, mouseY, delta);
		super.render(context, mouseX, mouseY, delta);

		assert this.minecraft != null;
		context.drawCenteredString(this.minecraft.font, Component.translatable("yacl3.config.variantsandventures:variantsandventures"), this.width / 2, 10, 0xFFFFFF);
	}

	@Override
	protected void init() {
		super.init();

		int fontHeight = this.font.lineHeight;
		DynamicGridWidget grid = new DynamicGridWidget(10, 10 + fontHeight + 10, width - 13, height - 20 - fontHeight - 10 - 20);

		grid.setPadding(3);

		grid.addChild(new ImageButtonWidget(0, 0, 0, 0, Component.translatable("yacl3.config.variantsandventures:variantsandventures.category.mod_mobs"), VariantsAndVentures.makeID("textures/gui/config/images/buttons/mod_mobs.webp"), btn -> {
			if(this.modMobsConfigScreen == null) {
				this.modMobsConfigScreen = ModMobsConfig.HANDLER.generateGui().generateScreen(this);
			}

			this.minecraft.setScreen(this.modMobsConfigScreen);
		}), 2, 1);

		grid.addChild(new ImageButtonWidget(0, 0, 0, 0, Component.translatable("yacl3.config.variantsandventures:variantsandventures.category.vanilla_mobs"), VariantsAndVentures.makeID("textures/gui/config/images/buttons/vanilla_mobs.webp"), btn -> {
			if(this.vanillaMobsConfigScreen == null) {
				this.vanillaMobsConfigScreen = VanillaMobsConfig.HANDLER.generateGui().generateScreen(this);
			}

			this.minecraft.setScreen(this.vanillaMobsConfigScreen);
		}), 2, 1);

		grid.calculateLayout();
		grid.visitWidgets(this::addRenderableWidget);

		int kofiButtonWidth = 135;
		int discordButtonWidth = 135;
		int discordAndKoFiButtonsWidth = kofiButtonWidth + discordButtonWidth + 30; // button widths + left margin of Ko-Fi button + right margin of Discord button
		int doneButtonWidth = this.width - discordAndKoFiButtonsWidth;
		var buttonWidget = Button.builder(CommonComponents.GUI_DONE, (btn) -> this.minecraft.setScreen(this.parent)).bounds(this.width / 2 - doneButtonWidth / 2, this.height - 30, doneButtonWidth, 20).build();
		var donateButton = Button.builder(Component.literal("Buy Me a Coffee").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD), (btn) -> Util.getPlatform().openUri("https://ko-fi.com/faboslav")).bounds(10, this.height - 30, kofiButtonWidth, 20).build();
		var discordButton = Button.builder(Component.literal("Join Our Discord").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD), (btn) -> Util.getPlatform().openUri("https://discord.gg/QGwFvvMQCn")).bounds(this.width - discordButtonWidth - 10, this.height - 30, discordButtonWidth, 20).build();

		this.addRenderableWidget(buttonWidget);
		this.addRenderableWidget(donateButton);
		this.addRenderableWidget(discordButton);
	}
}