package me.itskazos.bookgui.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.itskazos.bookgui.Main;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import net.minecraft.server.v1_8_R3.StatisticList;

public class CommandsBookGui implements CommandExecutor {
	private Plugin plugin = Main.getPlugin(Main.class);
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	Player p = (Player) sender;
        HumanEntity humanEntity = (HumanEntity) p;
        CraftHumanEntity craftHumanityEntity = (CraftHumanEntity) humanEntity;
        EntityHuman entityHuman = craftHumanityEntity.getHandle();
        
        
        
        ItemStack book = new ItemStack(Item.getById(387));
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("author", "ItsKazos");
        tag.setString("title", "Hi");
        tag.set("display", new NBTTagCompound());
        
        
        
        NBTTagCompound display = tag.getCompound("display");
        display.setString("Name", ChatColor.translateAlternateColorCodes('&', "Book GUI"));
        NBTTagList lore = new NBTTagList();
        lore.add(new NBTTagString("If you see this, please report it to an admin!"));
        display.set("Lore", lore);
        NBTTagList pages = new NBTTagList();
        pages.add(new NBTTagString("Go to the next page to test click events!"));
        
        
        
        NBTTagCompound json = new NBTTagCompound();
        json.setString("text", "Click Here!");
        json.set("clickEvent", new NBTTagCompound());
        json.set("hoverEvent", new NBTTagCompound());
        
        
        
        NBTTagCompound clickEvent = json.getCompound("clickEvent");
        clickEvent.setString("action", "run_command");
        clickEvent.setString("value", "/" + plugin.getConfig().getString("CustomCommand"));
        NBTTagCompound hoverEvent = json.getCompound("hoverEvent");
        hoverEvent.setString("action", "show_text");
        hoverEvent.set("value", new NBTTagCompound());
        hoverEvent.setString("insertion", "what is this?");
        NBTTagCompound hoverValue = hoverEvent.getCompound("value");
        hoverValue.setString("text", "Change this custom click command in the config.yml!");
        pages.add(new NBTTagString(json.toString()));
        
        
        
        
        tag.set("pages", pages);
        book.setTag(tag);
        org.bukkit.inventory.ItemStack hand = p.getItemInHand();
        p.setItemInHand(CraftItemStack.asBukkitCopy(book));
        entityHuman.openBook(book);
        p.setItemInHand(hand);
        entityHuman.b(StatisticList.USE_ITEM_COUNT[387]);
        return true;
    }
}
