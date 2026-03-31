package com.acorsicanfrog.apexwaters.client.model;

import com.acorsicanfrog.apexwaters.ApexWatersCommon;
import com.acorsicanfrog.apexwaters.client.animation.GreatWhiteSharkAnimations;
import com.acorsicanfrog.apexwaters.entity.GreatWhiteSharkEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

@SuppressWarnings("unused")
public class GreatWhiteSharkModel extends HierarchicalModel<GreatWhiteSharkEntity> 
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ApexWatersCommon.MOD_ID, "great_white_shark"), "main");
	
	private static final float SWIM_ANIMATION_SPEED_MULTIPLIER = 0.5F;
	private static final float CHASE_ANIMATION_SPEED_MULTIPLIER = 2.5F;

	private final ModelPart root;
	private final ModelPart section1;
	private final ModelPart Tetefull;
	private final ModelPart Tetehaut;
	private final ModelPart Machoireduhaut;
	private final ModelPart Tete;
	private final ModelPart bone;
	private final ModelPart teeth3;
	private final ModelPart Tetebas;
	private final ModelPart Machoiredubas;
	private final ModelPart Teeth;
	private final ModelPart Teeth2;
	private final ModelPart Dorsale;
	private final ModelPart Dorsalebas;
	private final ModelPart Dorsalehaut;
	private final ModelPart Rightaileron;
	private final ModelPart Leftaileron;
	private final ModelPart section2;
	private final ModelPart section3;
	private final ModelPart nageoireleft;
	private final ModelPart nageoireright;
	private final ModelPart section4;
	private final ModelPart Dorsale2;
	private final ModelPart Anale;
	private final ModelPart Section5;
	private final ModelPart Section6;
	private final ModelPart nageoirearrierehaut3;
	private final ModelPart nageoirearrierebas3;
	private final Vector3f animationCache = new Vector3f();

	public GreatWhiteSharkModel(ModelPart root) {
		this.root = root;
		this.section1 = root.getChild("section1");
		this.Tetefull = this.section1.getChild("Tetefull");
		this.Tetehaut = this.Tetefull.getChild("Tetehaut");
		this.Machoireduhaut = this.Tetehaut.getChild("Machoireduhaut");
		this.Tete = this.Machoireduhaut.getChild("Tete");
		this.bone = this.Tete.getChild("bone");
		this.teeth3 = this.Machoireduhaut.getChild("teeth3");
		this.Tetebas = this.Tetefull.getChild("Tetebas");
		this.Machoiredubas = this.Tetebas.getChild("Machoiredubas");
		this.Teeth = this.Machoiredubas.getChild("Teeth");
		this.Teeth2 = this.Teeth.getChild("Teeth2");
		this.Dorsale = this.section1.getChild("Dorsale");
		this.Dorsalebas = this.Dorsale.getChild("Dorsalebas");
		this.Dorsalehaut = this.Dorsale.getChild("Dorsalehaut");
		this.Rightaileron = this.section1.getChild("Rightaileron");
		this.Leftaileron = this.section1.getChild("Leftaileron");
		this.section2 = this.section1.getChild("section2");
		this.section3 = this.section2.getChild("section3");
		this.nageoireleft = this.section3.getChild("nageoireleft");
		this.nageoireright = this.section3.getChild("nageoireright");
		this.section4 = this.section3.getChild("section4");
		this.Dorsale2 = this.section4.getChild("Dorsale2");
		this.Anale = this.section4.getChild("Anale");
		this.Section5 = this.section4.getChild("Section5");
		this.Section6 = this.Section5.getChild("Section6");
		this.nageoirearrierehaut3 = this.Section6.getChild("nageoirearrierehaut3");
		this.nageoirearrierebas3 = this.Section6.getChild("nageoirearrierebas3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition section1 = partdefinition.addOrReplaceChild("section1", CubeListBuilder.create(), PartPose.offset(-0.4F, -4.0F, -37.0F));

		PartDefinition cube_r1 = section1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 133).addBox(-8.0F, -2.0F, -24.0F, 3.0F, 12.0F, 44.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6809F, 2.2F, 12.0953F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_r2 = section1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 133).addBox(5.0F, -2.0F, -24.0F, 3.0F, 12.0F, 44.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.7191F, 2.2F, 12.0953F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_r3 = section1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(1, 194).addBox(-13.0F, -2.0F, -24.0F, 15.0F, 18.0F, 44.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.3809F, -9.5F, 12.0953F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r4 = section1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 88).addBox(-2.0F, -2.0F, -24.0F, 15.0F, 18.0F, 44.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(1.4191F, -9.5F, 12.0953F, 0.0F, 0.0F, 0.2182F));

		PartDefinition Tetefull = section1.addOrReplaceChild("Tetefull", CubeListBuilder.create(), PartPose.offset(0.1191F, -5.1751F, -9.8781F));

		PartDefinition Tetehaut = Tetefull.addOrReplaceChild("Tetehaut", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.1F, 0.0F, -2.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r5 = Tetehaut.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(236, 124).mirror().addBox(-4.5F, -1.0F, -1.8F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.7452F, 1.384F, -18.4286F, 2.7989F, 0.9665F, -0.7218F));

		PartDefinition cube_r6 = Tetehaut.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(236, 124).addBox(-0.5F, -1.0F, -1.8F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7452F, 1.384F, -18.4286F, 2.7989F, -0.9665F, 0.7218F));

		PartDefinition cube_r7 = Tetehaut.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(232, 124).addBox(-3.5F, 0.8F, 0.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9F, 3.0F, -18.0F, 2.3623F, -1.1545F, 0.7323F));

		PartDefinition cube_r8 = Tetehaut.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(230, 124).addBox(-2.5F, 1.0F, 0.2F, 10.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, 1.5F, -18.6F, 2.3126F, 0.0F, 0.0F));

		PartDefinition cube_r9 = Tetehaut.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(233, 124).addBox(-3.5F, 0.8F, 0.0F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9F, -24.6F, 2.4435F, 0.0F, 0.0F));

		PartDefinition cube_r10 = Tetehaut.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(232, 124).mirror().addBox(-5.5F, 0.8F, 0.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.9F, 3.0F, -18.0F, 2.3623F, 1.1545F, -0.7323F));

		PartDefinition Machoireduhaut = Tetehaut.addOrReplaceChild("Machoireduhaut", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.8249F, -0.0266F, -0.1745F, 0.0F, 0.0F));

		PartDefinition Tete = Machoireduhaut.addOrReplaceChild("Tete", CubeListBuilder.create(), PartPose.offsetAndRotation(3.4F, -0.7474F, -0.1022F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r11 = Tete.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(2, 2).addBox(-9.0F, -2.0F, -12.0F, 12.0F, 8.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.8F, 3.0F, -10.0F, 0.1623F, -0.0892F, -0.2126F));

		PartDefinition cube_r12 = Tete.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(78, -1).mirror().addBox(-2.0F, -8.0F, -15.0F, 2.0F, 9.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.8F, 15.9274F, -0.8456F, -0.394F, -0.1248F, -0.3346F));

		PartDefinition cube_r13 = Tete.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(78, -1).addBox(0.0F, -8.0F, -15.0F, 2.0F, 9.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 15.9274F, -0.8456F, -0.394F, 0.1248F, 0.3346F));

		PartDefinition cube_r14 = Tete.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(52, 39).addBox(1.0F, -1.7972F, -8.5427F, 3.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9F, 1.1897F, -4.5335F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r15 = Tete.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(2, 40).addBox(-9.0F, -8.705F, -12.2547F, 12.0F, 6.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 12.4942F, -10.3315F, 0.1833F, 0.0F, 0.0F));

		PartDefinition cube_r16 = Tete.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 72).addBox(-3.0F, -2.0F, -12.0F, 12.0F, 8.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -10.0F, 0.1623F, 0.0892F, 0.2126F));

		PartDefinition bone = Tete.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-3.4F, 6.4064F, -21.4905F));

		PartDefinition cube_r17 = bone.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(117, 27).addBox(-5.1317F, -3.4961F, 3.0024F, 8.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7F, 7.3854F, -1.6306F, -1.5659F, -1.0517F, 2.9951F));

		PartDefinition cube_r18 = bone.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(117, 17).addBox(-2.8683F, -3.4961F, 3.0024F, 8.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7F, 7.3854F, -1.6306F, -1.5659F, 1.0517F, -2.9951F));

		PartDefinition cube_r19 = bone.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(77, 59).addBox(-6.0F, -5.1651F, -4.0137F, 12.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.1912F, -4.3254F, -0.1658F, 0.0F, 0.0F));

		PartDefinition cube_r20 = bone.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(89, 48).addBox(-6.1457F, -3.4286F, 0.7059F, 10.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.9637F, -2.1766F, -2.1722F, -1.0242F, -2.8065F));

		PartDefinition cube_r21 = bone.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(105, 2).addBox(-4.0F, -4.0014F, -0.0523F, 12.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.1477F, -5.5199F, 1.6232F, 0.0F, 0.0F));

		PartDefinition cube_r22 = bone.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(117, 48).addBox(-3.8543F, -3.4286F, 0.7059F, 10.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.9637F, -2.1766F, -2.1722F, 1.0242F, 2.8065F));

		PartDefinition cube_r23 = bone.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(78, 30).addBox(-5.0F, -4.6093F, -4.0795F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.8184F, -3.4723F, 0.4014F, 0.0F, 0.0F));

		PartDefinition teeth3 = Machoireduhaut.addOrReplaceChild("teeth3", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0249F, -22.4734F));

		PartDefinition cube_r24 = teeth3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(232, 130).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.9526F, 0.4329F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r25 = teeth3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(202, 139).addBox(-8.0F, -2.0F, -1.0F, 26.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -1.0526F, 7.3329F, -0.6448F, -1.3527F, 0.6448F));

		PartDefinition cube_r26 = teeth3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(204, 139).mirror().addBox(-18.0F, -2.0F, -1.0F, 26.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.5F, -1.0526F, 7.3329F, -0.6448F, 1.3527F, -0.6448F));

		PartDefinition cube_r27 = teeth3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(238, 124).mirror().addBox(-3.8F, 0.6F, 0.4F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3F, -0.2F, -1.1F, 2.6182F, 0.688F, -0.1268F));

		PartDefinition cube_r28 = teeth3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(230, 124).addBox(-5.5F, 0.8F, 0.0F, 11.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.4F, 2.7F, 13.5F, 2.3752F, -1.3431F, 0.7511F));

		PartDefinition cube_r29 = teeth3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(238, 124).addBox(0.8F, 0.6F, 0.4F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, -0.2F, -1.1F, 2.6182F, -0.688F, 0.1268F));

		PartDefinition Tetebas = Tetefull.addOrReplaceChild("Tetebas", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.1012F, 15.5168F, -1.8527F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r30 = Tetebas.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(237, 121).addBox(-0.9511F, -1.0949F, -4.5892F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, 0.0F, -17.7F, 1.6838F, -1.2786F, -1.6941F));

		PartDefinition cube_r31 = Tetebas.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(0.9137F, -1.1308F, -4.2905F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0024F, 0.0F, -18.0F, 1.6292F, 1.0615F, 1.6435F));

		PartDefinition cube_r32 = Tetebas.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(237, 121).addBox(2.1862F, -2.3197F, -2.0992F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, -15.4F, 2.6522F, 0.7759F, 2.2735F));

		PartDefinition Machoiredubas = Tetebas.addOrReplaceChild("Machoiredubas", CubeListBuilder.create(), PartPose.offsetAndRotation(7.5907F, 0.3387F, -0.0672F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r33 = Machoiredubas.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(152, 2).addBox(-7.0F, -2.9577F, -0.9094F, 12.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5895F, -4.7159F, -19.7555F, -1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r34 = Machoiredubas.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(156, 25).addBox(-4.0F, -3.0F, -12.0F, 14.0F, 4.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5895F, -1.2706F, 0.1199F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r35 = Machoiredubas.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(189, 2).addBox(-2.5F, -4.0003F, 0.7134F, 12.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0895F, -2.9265F, -18.8078F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r36 = Machoiredubas.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(202, 30).mirror().addBox(-3.2F, -2.0972F, -13.5427F, 7.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.8895F, -1.1782F, -4.3544F, -0.1384F, -0.0252F, -0.3025F));

		PartDefinition cube_r37 = Machoiredubas.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(202, 30).addBox(-3.8F, -2.0972F, -13.5427F, 7.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.2895F, -1.1782F, -4.3544F, -0.1384F, 0.0252F, 0.3025F));

		PartDefinition cube_r38 = Machoiredubas.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(208, 2).addBox(-0.6F, -3.0F, -10.0F, 2.0F, 5.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.1791F, -3.0F, -8.0F, 0.0307F, -0.1482F, -0.339F));

		PartDefinition cube_r39 = Machoiredubas.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(106, 59).addBox(-1.4F, -3.0F, -10.0F, 2.0F, 5.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -8.0F, 0.0307F, 0.1482F, 0.339F));

		PartDefinition Teeth = Machoiredubas.addOrReplaceChild("Teeth", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.4933F, -5.978F, -4.7242F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r40 = Teeth.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(236, 120).mirror().addBox(1.6362F, 0.2839F, -1.791F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8049F, -0.0606F, -0.9086F, -0.322F, -0.0594F, 0.0994F));

		PartDefinition cube_r41 = Teeth.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(236, 120).mirror().addBox(1.0295F, -0.9495F, -0.8319F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8049F, 0.1394F, -4.3086F, -0.0047F, 0.1352F, 0.3607F));

		PartDefinition cube_r42 = Teeth.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(-0.1145F, -0.5006F, -0.0472F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4049F, 0.0393F, -6.9086F, -0.0749F, 0.0775F, 0.3333F));

		PartDefinition cube_r43 = Teeth.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(0.4285F, -1.1148F, -1.5123F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4049F, 0.0393F, -6.9086F, 0.1413F, 0.3616F, 0.6751F));

		PartDefinition cube_r44 = Teeth.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(-2.5014F, -1.308F, -1.7632F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1049F, -0.0606F, -9.4086F, 2.8613F, -0.8655F, -2.8171F));

		PartDefinition cube_r45 = Teeth.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(-2.1014F, -0.1508F, -0.0622F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.1049F, -0.0606F, -11.6586F, 0.1497F, 0.7302F, 0.3649F));

		PartDefinition cube_r46 = Teeth.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(-1.7448F, -0.8269F, -4.2972F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8049F, -0.0606F, -11.9086F, -0.5687F, 1.3027F, -0.5364F));

		PartDefinition cube_r47 = Teeth.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(-1.5721F, -0.9399F, -2.6028F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8049F, -0.0606F, -11.9086F, -0.3512F, 1.1833F, -0.3241F));

		PartDefinition cube_r48 = Teeth.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(237, 121).mirror().addBox(-0.3194F, -0.9517F, -3.0834F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8049F, -0.0606F, -11.9086F, 0.3532F, 1.1822F, 0.3318F));

		PartDefinition Teeth2 = Teeth.addOrReplaceChild("Teeth2", CubeListBuilder.create(), PartPose.offset(-8.1925F, 0.0F, -4.0F));

		PartDefinition cube_r49 = Teeth2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(236, 120).addBox(-1.0295F, -0.9495F, -0.8319F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1951F, 0.1394F, -0.3086F, -0.0047F, -0.1352F, -0.3607F));

		PartDefinition cube_r50 = Teeth2.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(237, 121).addBox(0.1145F, -0.5006F, -0.0472F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5951F, 0.0393F, -2.9086F, -0.0749F, -0.0775F, -0.3333F));

		PartDefinition cube_r51 = Teeth2.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(237, 121).addBox(-0.4285F, -1.1148F, -1.5123F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5951F, 0.0393F, -2.9086F, 0.1413F, -0.3616F, -0.6751F));

		PartDefinition cube_r52 = Teeth2.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(237, 121).addBox(1.4054F, -0.4108F, 0.6072F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8951F, -0.0606F, -7.6586F, 0.1497F, -0.7302F, -0.3649F));

		PartDefinition cube_r53 = Teeth2.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(237, 121).addBox(2.3247F, -0.7558F, -4.1604F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.6951F, -0.1607F, -8.9086F, -2.0665F, -1.4079F, 2.0561F));

		PartDefinition cube_r54 = Teeth2.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(237, 121).addBox(2.3247F, -0.7558F, -4.1604F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1951F, -0.0606F, -7.9086F, -0.5687F, -1.3027F, 0.5364F));

		PartDefinition cube_r55 = Teeth2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(237, 121).addBox(1.5721F, -0.9399F, -2.6028F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1951F, -0.0606F, -7.9086F, -0.3512F, -1.1833F, 0.3241F));

		PartDefinition cube_r56 = Teeth2.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(237, 121).addBox(0.3194F, -0.9517F, -3.0834F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1951F, -0.0606F, -7.9086F, 0.3532F, -1.1822F, -0.3318F));

		PartDefinition cube_r57 = Teeth2.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(236, 120).addBox(-1.6362F, 0.2839F, -1.791F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1951F, -0.0606F, 3.0914F, -0.322F, 0.0594F, -0.0994F));

		PartDefinition Dorsale = section1.addOrReplaceChild("Dorsale", CubeListBuilder.create(), PartPose.offset(0.0191F, -12.0F, 20.0953F));

		PartDefinition Dorsalebas = Dorsale.addOrReplaceChild("Dorsalebas", CubeListBuilder.create().texOffs(1, 192).mirror().addBox(-1.5F, -3.3F, -5.3F, 3.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.3F, -1.7F));

		PartDefinition cube_r58 = Dorsalebas.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(1, 212).mirror().addBox(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 0.0F, 6.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r59 = Dorsalebas.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(1, 212).mirror().addBox(-3.0F, -1.7972F, -2.5427F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, -0.5F, -5.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition Dorsalehaut = Dorsale.addOrReplaceChild("Dorsalehaut", CubeListBuilder.create(), PartPose.offset(0.0F, -0.3602F, -0.4419F));

		PartDefinition cube_r60 = Dorsalehaut.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(24, 215).mirror().addBox(2.0F, -13.0F, -14.0F, 1.0F, 13.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 5.3602F, 6.2419F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r61 = Dorsalehaut.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(1, 216).addBox(-2.5F, -0.8F, -7.3F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.5F, -8.9F, 1.8F, -2.5307F, 0.0F, 0.0F));

		PartDefinition cube_r62 = Dorsalehaut.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(17, 230).mirror().addBox(2.5F, -0.8F, -6.3F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -4.9F, 4.5F, -1.789F, 0.0F, 0.0F));

		PartDefinition cube_r63 = Dorsalehaut.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(1, 225).mirror().addBox(2.5F, 0.2F, -6.3F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -3.0F, 1.0F, 1.9635F, 0.0F, 0.0F));

		PartDefinition Rightaileron = section1.addOrReplaceChild("Rightaileron", CubeListBuilder.create(), PartPose.offset(-10.5243F, 7.1169F, 9.1239F));

		PartDefinition cube_r64 = Rightaileron.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(89, 157).addBox(-31.0F, -1.0F, -11.0F, 32.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5434F, -1.1169F, 5.9714F, 0.0F, 0.48F, -0.3491F));

		PartDefinition cube_r65 = Rightaileron.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(-6, 149).addBox(-33.3F, -0.5F, 0.5F, 17.0F, 0.0F, 7.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(4.5434F, -1.1169F, 2.9714F, 0.0F, 0.2618F, -0.3491F));

		PartDefinition cube_r66 = Rightaileron.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(-5, 170).addBox(-4.2F, -0.5F, -3.6F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-26.0F, 10.0F, 15.0F, 3.1416F, -1.2654F, 2.7925F));

		PartDefinition cube_r67 = Rightaileron.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(1, 159).addBox(-18.0F, -1.0F, -11.0F, 13.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(4.5434F, -1.1169F, 2.9714F, 0.0F, 0.9599F, -0.3491F));

		PartDefinition Leftaileron = section1.addOrReplaceChild("Leftaileron", CubeListBuilder.create(), PartPose.offset(11.0191F, 7.3F, 10.0953F));

		PartDefinition cube_r68 = Leftaileron.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(-5, 170).addBox(-4.8F, -0.5F, -3.6F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(25.5434F, 9.8169F, 14.0286F, 3.1416F, 1.2654F, -2.7925F));

		PartDefinition cube_r69 = Leftaileron.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(-6, 149).addBox(16.3F, -0.5F, 0.5F, 17.0F, 0.0F, 7.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.0F, -1.3F, 2.0F, 0.0F, -0.2618F, 0.3491F));

		PartDefinition cube_r70 = Leftaileron.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(1, 159).addBox(5.0F, -1.0F, -11.0F, 13.0F, 1.0F, 7.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-5.0F, -1.3F, 2.0F, 0.0F, -0.9599F, 0.3491F));

		PartDefinition cube_r71 = Leftaileron.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(78, 227).addBox(-1.0F, -1.0F, -11.0F, 32.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -1.3F, 5.0F, 0.0F, -0.48F, 0.3491F));

		PartDefinition section2 = section1.addOrReplaceChild("section2", CubeListBuilder.create(), PartPose.offset(0.0191F, 1.8F, 34.1953F));

		PartDefinition cube_r72 = section2.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(78, 202).addBox(-3.0F, -4.0F, -1.0F, 7.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, -7.8F, -1.9F, -0.0749F, 0.0457F, 0.2603F));

		PartDefinition cube_r73 = section2.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(141, 105).addBox(-4.0F, -4.0F, -1.0F, 7.0F, 4.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8F, -7.8F, -1.9F, -0.0749F, -0.0457F, -0.2603F));

		PartDefinition cube_r74 = section2.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(208, 223).addBox(1.4572F, -3.0782F, -8.0F, 2.0F, 10.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2F, 8.3F, 5.9F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r75 = section2.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(137, 219).addBox(-11.0F, -1.0F, -24.0F, 13.0F, 14.0F, 21.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(-1.7F, -9.3F, 21.9F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r76 = section2.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(208, 223).addBox(-3.4572F, -3.0782F, -8.0F, 2.0F, 10.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.2F, 8.3F, 5.9F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r77 = section2.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(188, 179).addBox(-2.0F, -1.0F, -24.0F, 13.0F, 14.0F, 21.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.7F, -9.3F, 21.9F, 0.0F, 0.0F, 0.2182F));

		PartDefinition section3 = section2.addOrReplaceChild("section3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.6F, 20.5F));

		PartDefinition cube_r78 = section3.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(91, 181).mirror().addBox(-4.0F, -4.0F, -2.0F, 7.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, -3.7F, -1.4F, -0.1283F, -0.0149F, -0.436F));

		PartDefinition cube_r79 = section3.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(91, 181).addBox(-3.0F, -4.0F, -1.0F, 7.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.7F, -1.4F, -0.1283F, 0.0149F, 0.436F));

		PartDefinition cube_r80 = section3.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(4, 120).addBox(1.6572F, -3.7782F, -11.0F, 3.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 6.9F, 9.4F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r81 = section3.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(123, 192).addBox(-12.0F, 1.0F, -27.0F, 12.0F, 7.0F, 17.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.8F, -8.3F, 25.4F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r82 = section3.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(4, 120).addBox(-4.6572F, -3.7782F, -11.0F, 3.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 6.9F, 9.4F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r83 = section3.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(173, 151).addBox(0.0F, 1.0F, -27.0F, 12.0F, 7.0F, 17.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.8F, -8.3F, 25.4F, 0.0F, 0.0F, 0.4363F));

		PartDefinition nageoireleft = section3.addOrReplaceChild("nageoireleft", CubeListBuilder.create(), PartPose.offset(3.5F, 5.6F, 6.1F));

		PartDefinition cube_r84 = nageoireleft.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(183, 185).addBox(-1.6448F, -0.4F, -2.9879F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 0.0F, -0.3F, 0.0F, -0.48F, 0.5236F));

		PartDefinition nageoireright = section3.addOrReplaceChild("nageoireright", CubeListBuilder.create(), PartPose.offset(-3.2F, 5.8F, 6.4F));

		PartDefinition cube_r85 = nageoireright.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(183, 192).addBox(-5.0338F, -0.4866F, -3.4971F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0F, 0.48F, -0.5236F));

		PartDefinition section4 = section3.addOrReplaceChild("section4", CubeListBuilder.create(), PartPose.offset(0.0F, -0.3F, 16.4F));

		PartDefinition cube_r86 = section4.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(121, 168).addBox(-5.0F, 3.0F, -27.0F, 6.0F, 7.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.6F, -7.6F, 26.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r87 = section4.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(30, 113).addBox(-5.6572F, 0.2218F, -11.0F, 2.0F, 5.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(5.9F, 6.5F, 10.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r88 = section4.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(121, 168).addBox(-1.0F, 3.0F, -27.0F, 6.0F, 7.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.6F, -7.6F, 26.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r89 = section4.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(30, 113).addBox(3.6572F, 0.2218F, -11.0F, 2.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9F, 6.5F, 10.0F, 0.0F, 0.0F, -1.1781F));

		PartDefinition Dorsale2 = section4.addOrReplaceChild("Dorsale2", CubeListBuilder.create(), PartPose.offset(0.0F, -4.5F, 3.1F));

		PartDefinition cube_r90 = Dorsale2.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(236, 225).mirror().addBox(-3.0F, -5.0682F, -4.6555F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 2.0F, 0.4F, 0.5149F, 0.0F, 0.0F));

		PartDefinition Anale = section4.addOrReplaceChild("Anale", CubeListBuilder.create(), PartPose.offset(2.5F, 4.7F, 5.6F));

		PartDefinition cube_r91 = Anale.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(237, 186).mirror().addBox(-0.5F, -2.1472F, -2.1882F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 0.2F, 0.2F, 2.3911F, 0.0F, 0.0F));

		PartDefinition Section5 = section4.addOrReplaceChild("Section5", CubeListBuilder.create(), PartPose.offset(-0.1F, 0.1F, 11.0F));

		PartDefinition cube_r92 = Section5.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(208, 222).mirror().addBox(-3.0F, -4.0F, -1.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.6F, 0.1F, 0.3F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r93 = Section5.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(66, 158).addBox(0.0F, 5.0F, -27.0F, 4.0F, 6.0F, 10.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.2F, -8.2F, 27.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r94 = Section5.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(66, 158).addBox(-4.0F, 5.0F, -27.0F, 4.0F, 6.0F, 10.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.0F, -8.2F, 27.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition Section6 = Section5.addOrReplaceChild("Section6", CubeListBuilder.create(), PartPose.offset(0.1F, 1.1F, 10.0F));

		PartDefinition cube_r95 = Section6.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(224, 151).mirror().addBox(-2.0F, -4.0F, -10.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.4F, 0.7F, 10.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition nageoirearrierehaut3 = Section6.addOrReplaceChild("nageoirearrierehaut3", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 6.6F));

		PartDefinition cube_r96 = nageoirearrierehaut3.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(211, 72).addBox(-2.5F, -7.7F, -2.3F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.5F, -21.4237F, 15.7883F, -1.4835F, 0.0F, 0.0F));

		PartDefinition cube_r97 = nageoirearrierehaut3.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(212, 61).addBox(-2.5F, -1.4F, 2.0F, 0.0F, 10.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(2.5F, -21.9237F, 14.9883F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r98 = nageoirearrierehaut3.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(223, 56).addBox(-2.5F, -2.4F, -1.45F, 0.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -21.5237F, 15.8883F, -1.0908F, 0.0F, 0.0F));

		PartDefinition cube_r99 = nageoirearrierehaut3.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(236, 109).addBox(2.0F, -4.0F, -1.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.5F, 0.5F, -6.3F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r100 = nageoirearrierehaut3.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(230, 68).addBox(-3.0F, -20.0F, -12.0F, 1.0F, 20.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 5.0F, 4.9F, -0.6109F, 0.0F, 0.0F));

		PartDefinition nageoirearrierebas3 = Section6.addOrReplaceChild("nageoirearrierebas3", CubeListBuilder.create(), PartPose.offset(-0.1F, -1.9F, 9.5F));

		PartDefinition cube_r101 = nageoirearrierebas3.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(198, 97).addBox(-2.5F, -4.9F, 1.9F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.6F, 11.4054F, 5.2384F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r102 = nageoirearrierebas3.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(206, 92).addBox(-2.5F, -4.9F, -3.1F, 0.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, 11.4054F, 5.2384F, 1.0908F, 0.0F, 0.0F));

		PartDefinition cube_r103 = nageoirearrierebas3.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(196, 76).addBox(-3.0F, -36.0F, -11.0F, 1.0F, 10.0F, 10.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(2.6F, 26.0F, 24.0F, 0.6545F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(GreatWhiteSharkEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		boolean chasing = entity.isChasing();
		float animationSpeedMultiplier = chasing ? CHASE_ANIMATION_SPEED_MULTIPLIER : SWIM_ANIMATION_SPEED_MULTIPLIER;
		long animationTime = (long)(ageInTicks * 50.0F * animationSpeedMultiplier);

		if (chasing) {
			KeyframeAnimations.animate(this, GreatWhiteSharkAnimations.SWIM_CHASE, animationTime, 1.0F, this.animationCache);
		} else {
			KeyframeAnimations.animate(this, GreatWhiteSharkAnimations.SWIM_DEFAULT, animationTime, 1.0F, this.animationCache);
		}
	}
}