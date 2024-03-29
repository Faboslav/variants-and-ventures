// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class murk extends EntityModel<Entity> {
	private final ModelPart waist;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart corals;
	private final ModelPart coral1;
	private final ModelPart coral2;
	private final ModelPart rightArm;
	private final ModelPart rightItem;
	private final ModelPart leftArm;
	private final ModelPart leftItem;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	public murk(ModelPart root) {
		this.waist = root.getChild("waist");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData waist = modelPartData.addChild("waist", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

		ModelPartData body = waist.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData corals = head.addChild("corals", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData coral1 = corals.addChild("coral1", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, -15.0F, -4.0F, 9.0F, 9.0F, 0.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

		ModelPartData coral2 = corals.addChild("coral2", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, -6.0F, 5.0F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData rightItem = rightArm.addChild("rightItem", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 7.0F, 1.0F));

		ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData leftItem = leftArm.addChild("leftItem", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 7.0F, 1.0F));

		ModelPartData rightLeg = body.addChild("rightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData leftLeg = body.addChild("leftLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		waist.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}