PunishmentGUI is a free open source punishment system that allows you to add punishments through an easy to use, configurable GUI.

>> Adding a punishment
	Summary:
		Select a punishment
		Select duration
		Select reason
		Select if it's silent or not
		Confirm punishment

>> Customisability
	All of the GUIs are 100% customisable through config files. You can adjust everything to make it fit the theme of your server!
	![alt text](https://proxy.spigotmc.org/69f60634cc126120fbfd68d18182905b845d2a34?url=https%3A%2F%2Fimgur.com%2FfRf3P0q.png)
	
>> Config files
	Here is all of the config files
	config.yml:
	```
	#
	# MAIN CONFIGURATION FILE FOR PUNISHMENTGUI BY TOMSOZ
	# This is where you can configure and customise how PunishmentGUI works.
	# If you have any questions please check my github: https://github.com/Tomsoz125/PunishmentGUI
	# Thanks for using PunishmentGUI
	#
	config-version: 1

	#
	# Here is the commands that get executed for certain actions.
	# Set these to 'none' to disable them.
	#
	commands:
	  # The commands that get executed when a punishment is silent
	  silent:
		# The commands that get executed when a player is banned
		# Use: %target% to get the target's name
		# Use: %executor% to get the executor's name
		# Use: %reason% to get the ban reason
		# If applicable use: %time% to get the time
		ban:
		  - "ban %target% Banned by: %executor% for %reason%"
		# The commands that get executed when a player is unbanned
		unban:
		  - "none"
		# The commands that get executed when a player is tempbanned
		tempban:
		  - "ban %target% Banned by: %executor% for %reason%"
		# The commands that get executed when a player is muted
		mute:
		  - "none"
		# The commands that get executed when a player is unmuted
		unmute:
		  - "none"
		# The commands that get executed when a player is tempmuted
		tempmute:
		  - "none"
		# The commands that get executed when a player is warned
		warn:
		  - "none"
		# The commands that get executed when a player is tempwarned
		tempwarn:
		  - "none"
		# The commands that get executed when a player is unwarned
		unwarn:
		  - "none"
		# The commands that get executed when a player is kicked
		kick:
		  - "none"
	  # The commands that get executed when a punishment is public
	  public:
		# The commands that get executed when a player is banned
		ban:
		  - "none"
		# The commands that get executed when a player is unbanned
		unban:
		  - "none"
		# The commands that get executed when a player is tempbanned
		tempban:
		  - "none"
		# The commands that get executed when a player is muted
		mute:
		  - "none"
		# The commands that get executed when a player is unmuted
		unmute:
		  - "none"
		# The commands that get executed when a player is tempmuted
		tempmute:
		  - "none"
		# The commands that get executed when a player is warned
		warn:
		  - "none"
		# The commands that get executed when a player is tempwarned
		tempwarn:
		  - "none"
		# The commands that get executed when a player is unwarn
		unwarn:
		  - "none"
		# The commands that get executed when a player is kicked
		kick:
		  - "none"
	```
	selectpunishment.yml:
	```
	#
	# SELECTPUNISHMENT GUI CONFIG FILE FOR PUNISHMENTGUI BY TOMSOZ
	# This is where you can configure and customise how PunishmentGUI works.
	# If you have any questions please check my github: https://github.com/Tomsoz125/PunishmentGUI
	# Thanks for using PunishmentGUI
	#
	# This is some configurable options about the GUI
	gui:
	  # This is the name of the GUI
	  name: "&aSelect the punishment type"
	  # This is the number of rows in the GUI. Max 6
	  rows: 6
	  # This is the item that goes in all blank slots. Please refer to the wiki for a list of materials
	  blank:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GRAY_STAINED_GLASS_PANE'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
	# These are the items that go in the GUI
	items:
	  # This is the name. It can be anything.
	  placeholderPerm:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'LIGHT_GRAY_STAINED_GLASS_PANE'
		# This is the slot that the item goes in the GUI
		slot: 2
		# Because this is a placeholder this has to be 'PLACEHOLDER'
		type: 'PLACEHOLDER'
		# This is the name of the item
		name: "&aPermanent"
		# This is the lore of the item
		lore:
		  - "&aHere is a list of permanent punishments."
		permission: ''
		permission_lore:
		  - "&aHere is a list of permanent punishments."
	  # This is the name. It can be anything.
	  placeholderTemp:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'LIGHT_GRAY_STAINED_GLASS_PANE'
		# This is the slot that the item goes in the GUI
		slot: 5
		# Because this is a placeholder this has to be 'PLACEHOLDER'
		type: 'PLACEHOLDER'
		# This is the name of the item
		name: "&aTemporary"
		# This is the lore of the item
		lore:
		  - "&aHere is a list of temporary punishments."
		permission: ''
		permission_lore:
		  - "&aHere is a list of temporary punishments."
	  placeholderRevoke:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'LIGHT_GRAY_STAINED_GLASS_PANE'
		# This is the slot that the item goes in the GUI
		slot: 8
		# Because this is a placeholder this has to be 'PLACEHOLDER'
		type: 'PLACEHOLDER'
		# This is the name of the item
		name: "&aRevoke"
		# This is the lore of the item
		lore:
		  - "&aHere is a list of punishments you can revoke."
		permission: ''
		permission_lore:
		  - "&aHere is a list of punishments you can revoke."
	 
	  # This is the name. It can be anything.
	  banItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'DIAMOND_AXE'
		# This is the slot that the item goes in the GUI
		slot: 11
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'BAN'
		# This is the name of the item
		name: "&aBan"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to ban."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.ban'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  # This is the name. It can be anything.
	  muteItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'DIAMOND_AXE'
		# This is the slot that the item goes in the GUI
		slot: 20
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'MUTE'
		# This is the name of the item
		name: "&aMute"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to mute."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.mute'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	 
	  # This is the name. It can be anything.
	  kickItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'DIAMOND_AXE'
		# This is the slot that the item goes in the GUI
		slot: 29
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'KICK'
		# This is the name of the item
		name: "&aKick"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to kick."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.kick'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	 
	  # This is the name. It can be anything.
	  warnItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'DIAMOND_AXE'
		# This is the slot that the item goes in the GUI
		slot: 38
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'WARN'
		# This is the name of the item
		name: "&aWarn"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to warn."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.warn'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  # This is the name. It can be anything.
	  tempbanItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GOLDEN_AXE'
		# This is the slot that the item goes in the GUI
		slot: 14
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'TEMPBAN'
		# This is the name of the item
		name: "&aTemp-Ban"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to temp-ban."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.tempban'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  # This is the name. It can be anything.
	  tempmuteItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GOLDEN_AXE'
		# This is the slot that the item goes in the GUI
		slot: 23
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'TEMPMUTE'
		# This is the name of the item
		name: "&aTemp-Mute"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to temp-mute."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.tempmute'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	 
	  tempwarnItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GOLDEN_AXE'
		# This is the slot that the item goes in the GUI
		slot: 32
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'TEMPWARN'
		# This is the name of the item
		name: "&aTemp-Warn"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to temp-warn."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.tempwarn'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	 
	  placeholder1:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'IRON_BARS'
		# This is the slot that the item goes in the GUI
		slot: 41
		# Because this is a placeholder this has to be 'PLACEHOLDER'
		type: 'PLACEHOLDER'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
		# This is the permission for a user to use this punishment type
		permission: ''
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
	  # This is the name. It can be anything.
	  unbanItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'IRON_AXE'
		# This is the slot that the item goes in the GUI
		slot: 17
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'UNBAN'
		# This is the name of the item
		name: "&aUnban"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to unban."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.unban'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  # This is the name. It can be anything.
	  unmuteItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'IRON_AXE'
		# This is the slot that the item goes in the GUI
		slot: 26
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'UNMUTE'
		# This is the name of the item
		name: "&aUnmute"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to unmute."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.unmute'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  # This is the name. It can be anything.
	  unwarnItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'IRON_AXE'
		# This is the slot that the item goes in the GUI
		slot: 35
		# This is what the item does. Values: (BAN, TEMPBAN, UNBAN, UNWARN, TEMPWARN, WARN, KICK, MUTE, TEMPMUTE)
		type: 'UNWARN'
		# This is the name of the item
		name: "&aUnwarn"
		# This is the lore of the item
		lore:
		  - "&aClick here to set the punishment to unwarn."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.type.unwarn'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  placeholder2:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'IRON_BARS'
		# This is the slot that the item goes in the GUI
		slot: 44
		# Because this is a placeholder this has to be 'PLACEHOLDER'
		type: 'PLACEHOLDER'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
		# This is the permission for a user to use this punishment type
		permission: ''
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
	```
	selecttime.yml:
	```
	#
	# SELECTTIME GUI CONFIG FILE FOR PUNISHMENTGUI BY TOMSOZ
	# This is where you can configure and customise how PunishmentGUI works.
	# If you have any questions please check my github: https://github.com/Tomsoz125/PunishmentGUI
	# Thanks for using PunishmentGUI
	#
	# This is some configurable options about the GUI
	gui:
	  # This is the name of the GUI
	  name: "&aSelect the punishment time"
	  # This is the number of rows in the GUI. Max 6
	  rows: 3
	  # This is the item that goes in all blank slots. Please refer to the wiki for a list of materials
	  blank:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GRAY_STAINED_GLASS_PANE'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
	# These are the items that go in the GUI
	items:
	  # This is the name. It can be anything.
	  1moItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'RED_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 11
		# This is what the item stands for, use CUSTOM to set a custom time use PERM for permanent.
		time: '1mo'
		# This is the name of the item
		name: "&a1 month"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the time 1 month."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.time.1mo'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  1wItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GREEN_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 13
		# This is what the item stands for, use CUSTOM to set a custom time use PERM for permanent.
		time: '1w'
		# This is the name of the item
		name: "&a1 week"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the time 1 week."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.time.1w'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  1dItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'BLUE_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 15
		# This is what the item stands for, use CUSTOM to set a custom time use PERM for permanent.
		time: '1d'
		# This is the name of the item
		name: "&a1 day"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the time 1 day."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.time.1d'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  permItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'REDSTONE_BLOCK'
		# This is the slot that the item goes in the GUI
		slot: 17
		# This is what the item stands for, use CUSTOM to set a custom time use PERM for permanent.
		time: 'PERM'
		# This is the name of the item
		name: "&aForever"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the time Forever."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.time.forever'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  customItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'BOOK'
		# This is the slot that the item goes in the GUI
		slot: 23
		# This is what the item stands for, use CUSTOM to set a custom time use PERM for permanent.
		time: 'CUSTOM'
		# This is the name of the item
		name: "&aCustom time"
		# This is the lore of the item
		lore:
		  - "&aClick here to select a custom time."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.time.custom'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
selectreason.yml:
	```
	#
	# SELECTREASON GUI CONFIG FILE FOR PUNISHMENTGUI BY TOMSOZ
	# This is where you can configure and customise how PunishmentGUI works.
	# If you have any questions please check my github: https://github.com/Tomsoz125/PunishmentGUI
	# Thanks for using PunishmentGUI
	#
	# This is some configurable options about the GUI
	gui:
	  # This is the name of the GUI
	  name: "&aSelect the punishment reason"
	  # This is the number of rows in the GUI. Max 6
	  rows: 3
	  # This is the item that goes in all blank slots. Please refer to the wiki for a list of materials
	  blank:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GRAY_STAINED_GLASS_PANE'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
	# These are the items that go in the GUI
	items:
	  # This is the name. It can be anything.
	  cheatItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'RED_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 11
		# This is what the item stands for, use CUSTOM to set a custom reason or just type your reason in here
		reason: 'Cheating'
		# This is the name of the item
		name: "&aCheating"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the reason Cheating."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.reason.cheating'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  threatItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GREEN_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 13
		# This is what the item stands for, use CUSTOM to set a custom reason or just type your reason in here
		reason: 'Threats'
		# This is the name of the item
		name: "&aThreats"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the reason Threats."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.reason.threats'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  spamItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'BLUE_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 15
		# This is what the item stands for, use CUSTOM to set a custom reason or just type your reason in here
		reason: 'Spamming'
		# This is the name of the item
		name: "&aSpamming"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the reason Spamming."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.reason.spamming'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  permItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'REDSTONE_BLOCK'
		# This is the slot that the item goes in the GUI
		slot: 17
		# This is what the item stands for, use CUSTOM to set a custom reason or just type your reason in here
		reason: 'No reason'
		# This is the name of the item
		name: "&aNo reason"
		# This is the lore of the item
		lore:
		  - "&aClick here to select the time None."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.reason.none'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."
	  # This is the name. It can be anything.
	  customItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'BOOK'
		# This is the slot that the item goes in the GUI
		slot: 23
		# This is what the item stands for, use CUSTOM to set a custom reason or just type your reason in here
		time: 'CUSTOM'
		# This is the name of the item
		name: "&aCustom reason"
		# This is the lore of the item
		lore:
		  - "&aClick here to select a custom reason."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.reason.custom'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this time."

	```
	selectsilent.yml:
	```
	#
	# SELECTSILENT GUI CONFIG FILE FOR PUNISHMENTGUI BY TOMSOZ
	# This is where you can configure and customise how PunishmentGUI works.
	# If you have any questions please check my github: https://github.com/Tomsoz125/PunishmentGUI
	# Thanks for using PunishmentGUI
	#
	# This is some configurable options about the GUI
	gui:
	  # This is the name of the GUI
	  name: "&aSelect if it is silent"
	  # This is the number of rows in the GUI. Max 6
	  rows: 3
	  # This is the item that goes in all blank slots. Please refer to the wiki for a list of materials
	  blank:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GRAY_STAINED_GLASS_PANE'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
		  - ""
	# These are the items that go in the GUI
	items:
	  # This is the name. It can be anything.
	  silentItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'RED_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 16
		# This is what the item stands for, the only avaliable options are SILENT and PUBLIC.
		publicity: 'SILENT'
		# This is the name of the item
		name: "&aSilent"
		# This is the lore of the item
		lore:
		  - "&aClick here to select a silent punishment."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.publicity.silent'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this option."
	  # This is the name. It can be anything.
	  publicItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GREEN_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 12
		# This is what the item stands for, the only avaliable options are SILENT and PUBLIC.
		publicity: 'PUBLIC'
		# This is the name of the item
		name: "&aPublic"
		# This is the lore of the item
		lore:
		  - "&aClick here to select a public punishment."
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.publicity.public'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this option."
		```
	confirm.yml:
	```
	#
	# SELECTPUNISHMENT GUI CONFIG FILE FOR PUNISHMENTGUI BY TOMSOZ
	# This is where you can configure and customise how PunishmentGUI works.
	# If you have any questions please check my github: https://github.com/Tomsoz125/PunishmentGUI
	# Thanks for using PunishmentGUI
	#
	# This is some configurable options about the GUI
	gui:
	  # This is the name of the GUI
	  name: "&aAre you sure"
	  # This is the number of rows in the GUI. Max 6
	  rows: 3
	  # This is the item that goes in all blank slots. Please refer to the wiki for a list of materials
	  blank:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GRAY_STAINED_GLASS_PANE'
		# This is the name of the item
		name: "&7"
		# This is the lore of the item
		lore:
	# These are the items that go in the GUI
	items:
	  # This is the name. It can be anything.
	  confirmItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'GREEN_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 12
		# This is what the item does. Values: (CONFIRM, CANCEL)
		type: 'CONFIRM'
		# This is the name of the item
		name: "&aConfirm"
		# This is the lore of the item
		lore:
		  - "&aClick here to %punishment% %target% for %time%"
		  - "&afor the reason %reason% with publicity %publicity%"
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.confirm'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this punishment."
	  # This is the name. It can be anything.
	  cancelItem:
		# This is the item material. Please refer to the wiki for a list of materials
		material: 'RED_WOOL'
		# This is the slot that the item goes in the GUI
		slot: 16
		# This is what the item does. Values: (CONFIRM, CANCEL)
		type: 'CANCEL'
		# This is the name of the item
		name: "&aCancel"
		# This is the lore of the item
		lore:
		  - "&aClick here to cancel the punishment"
		# This is the permission for a user to use this punishment type
		permission: 'punishmentgui.cancel'
		# This is the lore displayed if a user dosen't have permission to use the punishment
		permission_lore:
		  - "&cYou don't have permission to use this."
	```
