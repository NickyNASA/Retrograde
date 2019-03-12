package com.nicky.retrograde.client;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.enums.MetalVariant;
import com.nicky.retrograde.api.interfaces.IHasMeta;
import com.nicky.retrograde.common.CommonProxy;
import com.nicky.retrograde.common.Reference;
import com.nicky.retrograde.common.RetrogradeBlocks;
import com.nicky.retrograde.common.RetrogradeItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{

	@Override
	public void registerItemRenders()
	{
		registerItemRender(RetrogradeItems.itemRuby);
		
		registerItemRender(RetrogradeItems.blueprint);
		registerItemRender(RetrogradeItems.nugget);
		registerItemRender(RetrogradeItems.ingot);
	}
	
	@Override
	public void registerBlockRenders()
	{
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RetrogradeBlocks.ruby), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + ItemNames.RUBY_BLOCK, "inventory"));
		
		
		
		for(MetalVariant metal : MetalVariant.values()){
			ModelResourceLocation loc = new ModelResourceLocation(Reference.MOD_ID + ":" + ItemNames.METAL_STORAGE, "type=" + metal.getName() + "_block");
			
			// Register the block for the blockstates model
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RetrogradeBlocks.metalStorage), metal.getId(), loc);
			
			// Register the block for the item model
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RetrogradeBlocks.metalStorage), metal.getId(), new ModelResourceLocation(Reference.MOD_ID + ":" + metal.getName() + "_block", "inventory"));
		}
		
		//ModelLoader.setCustomModelResourceLocation(item, metadata, model);
	}
	
	public void registerItemRender(Item item)
	{
		if(item instanceof IHasMeta){
			IHasMeta metaItem = (IHasMeta)item;
			
			for(int i = 0; i < metaItem.getVariants(); i++){
				
				ModelResourceLocation location = new ModelResourceLocation(Reference.MOD_ID + ":" + metaItem.getTexture(i), "inventory");
				ModelLoader.setCustomModelResourceLocation(item, i, location);
				
				//ModelBakery.registerItemVariants(item, new ResourceLocation(Reference.MOD_ID + ":" + metaItem.getTexture(i)));
			}
			
			return;
		}
		
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
