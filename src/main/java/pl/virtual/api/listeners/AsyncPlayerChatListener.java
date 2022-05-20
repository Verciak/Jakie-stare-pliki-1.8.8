package pl.virtual.api.listeners;

import java.util.regex.*;
import org.bukkit.event.player.*;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.*;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class AsyncPlayerChatListener implements Listener
{
    public static Pattern URL_PATTERN;
    public static Pattern IPPATTERN;
    public static Pattern BANNED_WORDS;
    
    static {
        URL_PATTERN = Pattern.compile("((?:(?:https?)://)?[\\a-_\\.]{2,})\\.([a-zA-Z]{2,3}(?:/\\S+)?)");
        IPPATTERN = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        BANNED_WORDS = Pattern.compile(".*(.ench|arivi.pl|easyhard|easyhc|easyhard.net|easyhc.pl|arivi|skkf|mchc|xcrafters|ssij|xkleszcz|craftstory|face2face|f2f|2/10|lagi|gale|algi|chuj|chuja|chujek|chuju|chujem|chujnia|chujowy|chujowa|chujowe|cipa|cipe|cipie|dojebac|dojebie|dojebal|dojebala|dojebalem|dojebalam|dojebie|dopieprzac|dopierdalac|dopierdala|dopierdalal|dopierdalala|dopierdoli|dopierdolil|dopierdole|dopierdoli|dopierdalajacy|dopierdolic|dupa|dupie|dupcia|dupeczka|dupy|dupe|huj|hujek|hujnia|huja|huje|hujem|huju|jebac|jebal|jebie|jebia|jebak|jebaka|jebal|jebany|jebane|jebanka|jebanko|jebankiem|jebanymi|jebana|jebanym|jebanej|jebana|jebani|jebanych|jebanymi|jebcie|jebiacy|jebiaca|jebiacego|jebiacej|jebia|jebie|jebliwy|jebnac|jebnal|jebna|jebnela|jebnie|jebnij|jebut|koorwa|korwa|kurestwo|kurew|kurewski|kurewska|kurewskiej|kurewska|kurewsko|kurewstwo|kurwa|kurwaa|kurwami|kurwe|kurwie|kurwiska|kurwo|kurwy|kurwach|kurwami|kurewski|kurwiarz|kurwi\u0105cy|kurwica|kurwic|kurwido\u0142ek|kurwik|kurwiki|kurwiszcze|kurwiszon|kurwiszona|kurwiszonem|kurwiszony|kutas|kutasa|kutasie|kutasem|kutasy|kutasow|kutasach|kutasami|matkojebca|matkojebcy|matkojebca|matkojebcami|matkojebcach|najebac|najebal|najebane|najebany|najebana|najebie|najebia|naopierdalac|naopierdalal|naopierdalala|napierdalac|napierdalajacy|napierdolic|nawpierdalac|nawpierdalal|nawpierdalala|obsrywac|obsrywajacy|odpieprzac|odpieprzy|odpieprzyl|odpieprzyla|odpierdalac|odpierdol|odpierdolil|odpierdolila|odpierdoli|odpierdalajacy|odpierdalajaca|odpierdolic|odpierdoli|opieprzaj\u0105cy|opierdalac|opierdala|opierdalajacy|opierdol|opierdolic|opierdoli|opierdola|piczka|pieprzniety|pieprzony|pierdel|pierdlu|pierdola|pierdolacy|pierdolaca|pierdol|pierdole|pierdolenie|pierdoleniem|pierdoleniu|pierdolec|pierdola|pierdolicie|pierdolic|pierdolil|pierdolila|pierdoli|pierdolniety|pierdolisz|pierdolnac|pierdolnal|pierdolnela|pierdolnie|pierdolnij|pierdolnik|pierdolona|pierdolone|pierdolony|pierdz\u0105cy|pierdziec|pizda|pizde|pizdzie|pizdnac|pizdu|podpierdalac|podpierdala|podpierdalajacypodpierdolic|podpierdoli|pojeb|pojeba|pojebami|pojebani|pojebanego|pojebanemu|pojebani|pojebany|pojebanych|pojebanym|pojebanymi|pojebem|pojebac|pojebalo|popierdala|popierdalac|popierdolic|popierdoli|popierdolonego|popierdolonemu|popierdolonym|popierdolone|popierdoleni|popierdolony|porozpierdala|porozpierdalac|poruchac|przejebane|przejebac|przyjebali|przepierdalac|przepierdala|przepierdalajacy|przepierdalajaca|przepierdolic|przyjebac|przyjebie|przyjebala|przyjebal|przypieprzac|przypieprzajacy|przypieprzajaca|przypierdalac|przypierdala|przypierdoli|przypierdalajacy|przypierdolic|qrwa|rozjebac|rozjebie|rozjeba\u0142a|rozpierdalac|rozpierdala|rozpierdolic|rozpierdole|rozpierdoli|rozpierducha|skurwiel|skurwiela|skurwielem|skurwielu|skurwysyn|skurwysynow|skurwysyna|skurwysynem|skurwysynu|skurwysyny|skurwysynski|skurwysynstwo|spieprzac|spieprza|spieprzaj|spieprzajcie|spieprzaja|spieprzajacy|spieprzajaca|spierdalac|spierdala|spierdalal|spierdalalcie|spierdalala|spierdalajacy|spierdolic|spierdoli|spierdol\u0105|spierdola|srac|srajacy|srajac|sraj|sukinsyn|sukinsyny|sukinsynom|sukinsynowi|sukinsynow|ujebac|ujebal|ujebana|ujebany|ujebie|ujeba\u0142a|ujebala|upierdalac|upierdala|upierdoli|upierdolic|upierdoli|upierdola|upierdoleni|wjebac|wjebie|wjebia|wjebiemy|wjebiecie|wkurwiac|wkurwi|wkurwia|wkurwial|wkurwiajacy|wkurwiajaca|wkurwic|wkurwi|wkurwiacie|wkurwiali|wkurwia|wkurwimy|wkurwicie|wkurwiacie|wkurwic|wkurwia|wpierdalac|wpierdalajacy|wpierdol|wpierdolic|wpizdu|wyjebac|wyjebali|wyjebac|wyjebie|wyjebia|wyjebiesz|wyjebie|wyjebiecie|wyjebiemy|wypieprzac|wypieprza|wypieprzal|wypieprzala|wypieprzy|wypieprzyla|wypieprzyl|wypierdal|wypierdalac|wypierdala|wypierdalaj|wypierdalal|wypierdalala|wypierdolic|wypierdoli|wypierdolimy|wypierdolicie|wypierdola|wypierdolili|wypierdolil|wypierdolila|zajebac|zajebie|zajebia|zajebial|zajebiala|zajebali|zajebana|zajebani|zajebane|zajebany|zajebanych|zajebanym|zajebanymi|zajebiste|zajebisty|zajebistych|zajebista|zajebistym|zajebistymi|zajebiscie|zapieprzyc|zapieprzy|zapieprzyl|zapieprzyla|zapieprza|zapieprz|zapieprzymy|zapieprzycie|zapieprzysz|zapierdala|zapierdalac|zapierdalaja|zapierdalaj|zapierdalajcie|zapierdalala|zapierdalali|zapierdalajacy|zapierdolic|zapierdoli|zapierdolil|zapierdolila|zapierdola|zapierniczac|zapierniczajacy|zasrac|zasranym|zasrywajacy|zesrywac|zesrywajac|zjebac|zjebal|zjebala|zjebana|zjebia|zjebali|zjeby+).*");
    }
    
    
    
	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		final Player p2 = e.getPlayer();
		final User u = UserManager.getUser(p2);
        final Mute m = MuteManager.getMute(p2);
        final Guild g = GuildManager.getGuild(p2);
		if (u == null) {
			e.setCancelled(true);
			return;
		}
		if (e.getMessage().startsWith("!help") && g != null) {
			e.setCancelled(true);
			return;
		}
		if (e.getMessage().startsWith("!") && g != null) {
			e.setCancelled(true);
			return;
		}
		if (e.getMessage().startsWith("!!") && g != null) {
			e.setCancelled(true);
			return;
		}
		if (!p2.hasPermission("tools.cmd.helper") && Config.LVL > u.getLvl()) {
			ChatUtil.sendMessage((CommandSender) p2, "&9&lERROR: &7Na chacie mozesz pisac od: &c" + Config.LVL + " &7lvl");
			e.setCancelled(true);
			return;
		}
		if (!p2.hasPermission("tools.cmd.chatvip") && ChatManager.vipChat) {
			ChatUtil.sendMessage((CommandSender) p2, "&9&lERROR: &7Chat jest wlaczony tylko dla rang &c&lPremium");
			e.setCancelled(true);
			return;
		}
		if (!p2.hasPermission("tools.cmd.helper") && !ChatManager.enable && !ChatManager.vipChat) {
			ChatUtil.sendMessage((CommandSender) p2, "&9&lERROR: &7Chat jest wylaczony");
			e.setCancelled(true);
			return;
		}
		if (!p2.hasPermission("tools.cmd.helper") && !u.isChat()) {
			ChatUtil.sendMessage((CommandSender) p2, "&9&lERROR: &7Nie mozesz tak szybko pisac &7(&f" + DataUtil.secondsToString(u.getLastChat()) + "&7)");
			e.setCancelled(true);
			return;
		}
		if (!p2.hasPermission("core.nomute") && m != null) {
			ChatUtil.sendMessage((CommandSender) p2, "&9&lERROR: &7Jestes wyciszony");
			e.setCancelled(true);
			return;
		}		
		String message = e.getMessage();
		if (!p2.hasPermission("tools.chat.link")) {
			final String[] block = { ".pl", ".ench", ".msrv", ".eu", ".com", ".net" };
			String[] array;
			for (int length = (array = block).length, i = 0; i < length; ++i) {
				final String s = array[i];
				if (message.contains(s)) {
					message = ChatUtil.fixColor("REKLAMA");
				}
			}
		}
		String globalFormat = "";
		LuckPerms api = LuckPermsProvider.get();
		final String groupName = api.getUserManager().getUser(e.getPlayer().getUniqueId()).getPrimaryGroup();
		String[] array2;
		String tag = "";
		if (u.getGuild() != null) {
			tag = "&8[&c" + u.getGuild().getTag() + "&8] ";
		}
		if (groupName.equalsIgnoreCase("root")) {
			globalFormat = "&8(&4&lW&8) &7" + p2.getName() + "&8 » &4";
		} else if (groupName.equalsIgnoreCase("dev")) {
			globalFormat = "&8(&4&lT&8) &7" + p2.getName() + "&8 » &4";
		} else if (groupName.equalsIgnoreCase("ha")) {
			globalFormat = "&8(&4&lH@&8) &7" + p2.getName() + "&8 » &c";
		} else if (groupName.equalsIgnoreCase("admin")) {
			globalFormat = "&8(&c&lA&8) &7" + p2.getName() + "&8 » &c";
		} else if (groupName.equalsIgnoreCase("moderator")) {
			globalFormat = "&8(&2&lM&8) &7" + p2.getName() + "&8 » &5";
		} else if (groupName.equalsIgnoreCase("helper")) {
			globalFormat = "&8(&b&lH&8) &7" + p2.getName() + "&8 » &a";
		} else if (groupName.equalsIgnoreCase("yt")) {
			globalFormat = tag +"&8[&4&lY&F&lT&8]&7 " + p2.getName() + "&8 » &f";
		} else if (groupName.equalsIgnoreCase("yt+")) {
			globalFormat = tag +"&8[&4&lY&F&lT&6&l+&8]&7 " + p2.getName() + "&8 » &f";
		} else if (groupName.equalsIgnoreCase("VIP")) {
			globalFormat = tag +"&8[&e&lVIP&8]&7 " + p2.getName() + "&8 » &f";
		} else if (groupName.equalsIgnoreCase("SVIP")) {
			globalFormat = tag +"&8[&6&lSVIP&8]&7 " + p2.getName() + "&8 » &f";
		} else if (groupName.equalsIgnoreCase("DARK")) {
			globalFormat = tag +"&8[&8&lDARK&8]&7 " + p2.getName() + "&8 » &f";
		} else if (groupName.equalsIgnoreCase("twitch")) {
			globalFormat = tag +"&8[&5&lTwitch&8]&7 " + p2.getName() + "&8 » &f";
		} else if (groupName.equalsIgnoreCase("default")) {
			globalFormat = ""+ tag + "&7" + p2.getName() + "&8 » &f";
		} else {
			globalFormat = ""+ tag + "&7" + p2.getName() + "&8 » &f";
		}
		globalFormat = ChatUtil.fixColor(globalFormat);
		if (p2.hasPermission("tools.chat.bypass")) {
			message = ChatUtil.fixColor(message);
		}
		message.replaceAll("%", "");
		message.replace("@ff&f", "ffL &4&l\u2764&f");
		message.replace("#<3&f", "&4&l\u2764&f");
		message.replace("#X&f", "&c\u2716&f");
		message.replace("#>>&f", "&e»&f");
		message.replace("#<<&f", "&e«&f");
		message.replace("#V&f", "&2\u2714&f");
		message.replace("@xdf", "&cdddA&f");
		globalFormat = String.valueOf(globalFormat) + message.replace("%", "")
				.replace("#V", ChatUtil.fixColor("&2\u2714&f")).replace("#X", ChatUtil.fixColor("&c\u2716&f"))
				.replace("#<3", ChatUtil.fixColor("&4&l\u2764&f"))
				.replace("#>>", ChatUtil.fixColor("&e»&f"))
				.replace("#<<", ChatUtil.fixColor("&e«&f"));
		Bukkit.broadcastMessage(globalFormat);
		u.setLastChat(0L);
		u.setLastChat(System.currentTimeMillis() + TimeUtil.SECOND.getTime(Config.CHAT_SLOWMODE));
	}

	static {
		URL_PATTERN = Pattern.compile("((?:(?:https?)://)?[\\a-_\\.]{2,})\\.([a-zA-Z]{2,3}(?:/\\S+)?)");
		IPPATTERN = Pattern.compile(
				"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		BANNED_WORDS = Pattern.compile(
				".*(.ench|arivi.pl|easyhard|easyhc|easyhard.net|easyhc.pl|arivi|skkf|mchc|xcrafters|ssij|craftstory||chuj|chuja|chujek|chuju|chujem|chujnia|chujowy|chujowa|chujowe|cipa|cipe|cipie|dojebac|dojebie|dojebal|dojebala|dojebalem|dojebalam|dojebie|dopieprzac|dopierdalac|dopierdala|dopierdalal|dopierdalala|dopierdoli|dopierdolil|dopierdole|dopierdoli|dopierdalajacy|dopierdolic|dupa|dupie|dupcia|dupeczka|dupy|dupe|huj|hujek|hujnia|huja|huje|hujem|huju|jebac|jebal|jebie|jebia|jebak|jebaka|jebal|jebany|jebane|jebanka|jebanko|jebankiem|jebanymi|jebana|jebanym|jebanej|jebana|jebani|jebanych|jebanymi|jebcie|jebiacy|jebiaca|jebiacego|jebiacej|jebia|jebie|jebliwy|jebnac|jebnal|jebna|jebnela|jebnie|jebnij|jebut|koorwa|korwa|kurestwo|kurew|kurewski|kurewska|kurewskiej|kurewska|kurewsko|kurewstwo|kurwa|kurwaa|kurwami|kurwe|kurwie|kurwiska|kurwo|kurwy|kurwach|kurwami|kurewski|kurwiarz|kurwi\u0105cy|kurwica|kurwic|kurwido\u0142ek|kurwik|kurwiki|kurwiszcze|kurwiszon|kurwiszona|kurwiszonem|kurwiszony|kutas|kutasa|kutasie|kutasem|kutasy|kutasow|kutasach|kutasami|matkojebca|matkojebcy|matkojebca|matkojebcami|matkojebcach|najebac|najebal|najebane|najebany|najebana|najebie|najebia|naopierdalac|naopierdalal|naopierdalala|napierdalac|napierdalajacy|napierdolic|nawpierdalac|nawpierdalal|nawpierdalala|obsrywac|obsrywajacy|odpieprzac|odpieprzy|odpieprzyl|odpieprzyla|odpierdalac|odpierdol|odpierdolil|odpierdolila|odpierdoli|odpierdalajacy|odpierdalajaca|odpierdolic|odpierdoli|opieprzaj\u0105cy|opierdalac|opierdala|opierdalajacy|opierdol|opierdolic|opierdoli|opierdola|piczka|pieprzniety|pieprzony|pierdel|pierdlu|pierdola|pierdolacy|pierdolaca|pierdol|pierdole|pierdolenie|pierdoleniem|pierdoleniu|pierdolec|pierdola|pierdolicie|pierdolic|pierdolil|pierdolila|pierdoli|pierdolniety|pierdolisz|pierdolnac|pierdolnal|pierdolnela|pierdolnie|pierdolnij|pierdolnik|pierdolona|pierdolone|pierdolony|pierdz\u0105cy|pierdziec|pizda|pizde|pizdzie|pizdnac|pizdu|podpierdalac|podpierdala|podpierdalajacypodpierdolic|podpierdoli|pojeb|pojeba|pojebami|pojebani|pojebanego|pojebanemu|pojebani|pojebany|pojebanych|pojebanym|pojebanymi|pojebem|pojebac|pojebalo|popierdala|popierdalac|popierdolic|popierdoli|popierdolonego|popierdolonemu|popierdolonym|popierdolone|popierdoleni|popierdolony|porozpierdala|porozpierdalac|poruchac|przejebane|przejebac|przyjebali|przepierdalac|przepierdala|przepierdalajacy|przepierdalajaca|przepierdolic|przyjebac|przyjebie|przyjebala|przyjebal|przypieprzac|przypieprzajacy|przypieprzajaca|przypierdalac|przypierdala|przypierdoli|przypierdalajacy|przypierdolic|qrwa|rozjebac|rozjebie|rozjeba\u0142a|rozpierdalac|rozpierdala|rozpierdolic|rozpierdole|rozpierdoli|rozpierducha|skurwiel|skurwiela|skurwielem|skurwielu|skurwysyn|skurwysynow|skurwysyna|skurwysynem|skurwysynu|skurwysyny|skurwysynski|skurwysynstwo|spieprzac|spieprza|spieprzaj|spieprzajcie|spieprzaja|spieprzajacy|spieprzajaca|spierdalac|spierdala|spierdalal|spierdalalcie|spierdalala|spierdalajacy|spierdolic|spierdoli|spierdol\u0105|spierdola|srac|srajacy|srajac|sraj|sukinsyn|sukinsyny|sukinsynom|sukinsynowi|sukinsynow|ujebac|ujebal|ujebana|ujebany|ujebie|ujeba\u0142a|ujebala|upierdalac|upierdala|upierdoli|upierdolic|upierdoli|upierdola|upierdoleni|wjebac|wjebie|wjebia|wjebiemy|wjebiecie|wkurwiac|wkurwi|wkurwia|wkurwial|wkurwiajacy|wkurwiajaca|wkurwic|wkurwi|wkurwiacie|wkurwiali|wkurwia|wkurwimy|wkurwicie|wkurwiacie|wkurwic|wkurwia|wpierdalac|wpierdalajacy|wpierdol|wpierdolic|wpizdu|wyjebac|wyjebali|wyjebac|wyjebie|wyjebia|wyjebiesz|wyjebie|wyjebiecie|wyjebiemy|wypieprzac|wypieprza|wypieprzal|wypieprzala|wypieprzy|wypieprzyla|wypieprzyl|wypierdal|wypierdalac|wypierdala|wypierdalaj|wypierdalal|wypierdalala|wypierdolic|wypierdoli|wypierdolimy|wypierdolicie|wypierdola|wypierdolili|wypierdolil|wypierdolila|zajebac|zajebie|zajebia|zajebial|zajebiala|zajebali|zajebana|zajebani|zajebane|zajebany|zajebanych|zajebanym|zajebanymi|zajebiste|zajebisty|zajebistych|zajebista|zajebistym|zajebistymi|zajebiscie|zapieprzyc|zapieprzy|zapieprzyl|zapieprzyla|zapieprza|zapieprz|zapieprzymy|zapieprzycie|zapieprzysz|zapierdala|zapierdalac|zapierdalaja|zapierdalaj|zapierdalajcie|zapierdalala|zapierdalali|zapierdalajacy|zapierdolic|zapierdoli|zapierdolil|zapierdolila|zapierdola|zapierniczac|zapierniczajacy|zasrac|zasranym|zasrywajacy|zesrywac|zesrywajac|zjebac|zjebal|zjebala|zjebana|zjebia|zjebali|zjeby).*");
	}
}
