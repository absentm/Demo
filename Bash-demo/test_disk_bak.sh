
##############################################################################

# lsblk    sas3ircu   smartctl 

# Get SAS version code

lspci | grep -i lsi
sas2ircu list

lsblk
smartctl -H /dev/sdb
ll /dev/disk/by-id/ | grep sdb
sas2ircu 0 display

##############################################################################

# get disk name info
>>> lsblk

[root@AbsentM ~]# lsblk

sda      8:0    0 557.9G  0 disk 
├─sda1   8:1    0     1M  0 part 
├─sda2   8:2    0   500M  0 part /boot
├─sda3   8:3    0 299.4G  0 part 
├─sda4   8:4    0     1K  0 part 
├─sda5   8:5    0  97.7G  0 part /
├─sda6   8:6    0  97.7G  0 part /var/log
├─sda7   8:7    0  62.5G  0 part [SWAP]
└─sda8   8:8    0   196M  0 part /boot/efi
sdb      8:16   0   2.7T  0 disk 
├─sdb1   8:17   0   2.7T  0 part /var/lib/icfs/osd/icfs-0
└─sdb2   8:18   0     5G  0 part 
sdc      8:32   0   2.7T  0 disk 
├─sdc1   8:33   0   2.7T  0 part /var/lib/icfs/osd/icfs-1
└─sdc2   8:34   0     5G  0 part 
sdd      8:48   0 447.1G  0 disk 
└─sdd1   8:49   0    50G  0 part /var/lib/icfs/mon
sde      8:64   0   2.7T  0 disk 
├─sde1   8:65   0   2.7T  0 part /var/lib/icfs/osd/icfs-2
└─sde2   8:66   0     5G  0 part 
sdf      8:80   0   2.7T  0 disk 
├─sdf1   8:81   0   2.7T  0 part /var/lib/icfs/osd/icfs-3
└─sdf2   8:82   0     5G  0 part 
sdg      8:96   0   2.7T  0 disk 
├─sdg1   8:97   0   2.7T  0 part /var/lib/icfs/osd/icfs-4
└─sdg2   8:98   0     5G  0 part 
sdh      8:112  0   2.7T  0 disk 
├─sdh1   8:113  0   2.7T  0 part /var/lib/icfs/osd/icfs-5
└─sdh2   8:114  0     5G  0 part 
sdi      8:128  0   2.7T  0 disk 
├─sdi1   8:129  0   2.7T  0 part /var/lib/icfs/osd/icfs-6
└─sdi2   8:130  0     5G  0 part 
sdj      8:144  0   2.7T  0 disk 
├─sdj1   8:145  0   2.7T  0 part /var/lib/icfs/osd/icfs-7
└─sdj2   8:146  0     5G  0 part 
sdk      8:160  0   2.7T  0 disk 
├─sdk1   8:161  0   2.7T  0 part /var/lib/icfs/osd/icfs-8
└─sdk2   8:162  0     5G  0 part 
sdl      8:176  0   2.7T  0 disk 
├─sdl1   8:177  0   2.7T  0 part /var/lib/icfs/osd/icfs-9
└─sdl2   8:178  0     5G  0 part

##############################################################################

# Get disk health info: PASSED is up, Failure is down
>>> smartctl -H /dev/sdb

[root@AbsentM ~]# smartctl -H /dev/sdb
smartctl 6.2 2013-07-26 r3841 [x86_64-linux-3.10.0-327.el7.x86_64] (local build)
Copyright (C) 2002-13, Bruce Allen, Christian Franke, www.smartmontools.org

=== START OF READ SMART DATA SECTION ===
SMART overall-health self-assessment test result: PASSED


##############################################################################

# Get disk serial no, eg: sdb's serial no is PN1234P8J56YVP
>>> ll /dev/disk/by-id/ | grep sdb

[root@AbsentM ~]# ll /dev/disk/by-id/ | grep sdb
lrwxrwxrwx 1 root root  9 Feb 28 20:26 ata-HGST_HUS724030ALA640_PN1234P8J56YVP -> ../../sdb
lrwxrwxrwx 1 root root 10 Feb 28 20:26 ata-HGST_HUS724030ALA640_PN1234P8J56YVP-part1 -> ../../sdb1
lrwxrwxrwx 1 root root 10 Feb 28 20:26 ata-HGST_HUS724030ALA640_PN1234P8J56YVP-part2 -> ../../sdb2
lrwxrwxrwx 1 root root  9 Feb 28 20:26 wwn-0x8f002cdecca25000 -> ../../sdb
lrwxrwxrwx 1 root root 10 Feb 28 20:26 wwn-0x8f002cdecca25000-part1 -> ../../sdb1
lrwxrwxrwx 1 root root 10 Feb 28 20:26 wwn-0x8f002cdecca25000-part2 -> ../../sdb2

##############################################################################

# Get disk slot info
>>> sas2ircu 0 display

[root@AbsentM ~]# sas2ircu 0 display
LSI Corporation SAS2 IR Configuration Utility.
Version 20.00.00.00 (2014.09.18) 
Copyright (c) 2008-2014 LSI Corporation. All rights reserved. 

Read configuration has been initiated for controller 0
------------------------------------------------------------------------
Controller information
------------------------------------------------------------------------
  Controller type                         : SAS2308_1
  BIOS version                            : 7.29.00.00
  Firmware version                        : 15.00.00.00
  Channel description                     : 1 Serial Attached SCSI
  Initiator ID                            : 0
  Maximum physical devices                : 255
  Concurrent commands supported           : 3072
  Slot                                    : 11
  Segment                                 : 0
  Bus                                     : 1
  Device                                  : 0
  Function                                : 0
  RAID Support                            : Yes
------------------------------------------------------------------------
IR Volume information
------------------------------------------------------------------------
IR volume 1
  Volume ID                               : 286
  Status of volume                        : Okay (OKY)
  Volume wwid                             : 05c9134ab526c1fe
  RAID level                              : RAID1
  Size (in MB)                            : 571250
  Boot                                    : Primary
  Physical hard disks                     :
  PHY[0] Enclosure#/Slot#                 : 1:0
  PHY[1] Enclosure#/Slot#                 : 1:1
------------------------------------------------------------------------
Physical device information
------------------------------------------------------------------------
Initiator at ID #0

Device is a Hard disk
  Enclosure #                             : 1
  Slot #                                  : 0
  SAS Address                             : 5000cca-0-7076-baad
  State                                   : Optimal (OPT)
  Size (in MB)/(in sectors)               : 572325/1172123567
  Manufacturer                            : HITACHI 
  Model Number                            : HUC109060CSS600 
  Firmware Revision                       : A440
  Serial No                               : W7J3969G
  GUID                                    : 5000cca07076baac
  Protocol                                : SAS
  Drive Type                              : SAS_HDD

Device is a Hard disk
  Enclosure #                             : 1
  Slot #                                  : 1
  SAS Address                             : 5000cca-0-7077-a615
  State                                   : Optimal (OPT)
  Size (in MB)/(in sectors)               : 572325/1172123567
  Manufacturer                            : HITACHI 
  Model Number                            : HUC109060CSS600 
  Firmware Revision                       : A440
  Serial No                               : W7J3TW9G
  GUID                                    : 5000cca07077a614
  Protocol                                : SAS
  Drive Type                              : SAS_HDD

Device is a Hard disk
  Enclosure #                             : 1
  Slot #                                  : 4
  SAS Address                             : 4433221-1-0400-0000
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 457862/937703087
  Manufacturer                            : ATA     
  Model Number                            : INTEL SSDSC2BB48
  Firmware Revision                       : 0140
  Serial No                               : PHWA627301U3480FGN
  GUID                                    : 55cd2e404c7e7028
  Protocol                                : SATA
  Drive Type                              : SATA_SSD

Device is a Hard disk
  Enclosure #                             : 1
  Slot #                                  : 5
  SAS Address                             : 4433221-1-0500-0000
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 2861588/5860533167
  Manufacturer                            : ATA     
  Model Number                            : HGST HUS724030AL
  Firmware Revision                       : AB70
  Serial No                               : PN1234P8J56YVP
  GUID                                    : 5000cca22cde8f00
  Protocol                                : SATA
  Drive Type                              : SATA_HDD

Device is a Hard disk
  Enclosure #                             : 1
  Slot #                                  : 6
  SAS Address                             : 4433221-1-0600-0000
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 2861588/5860533167
  Manufacturer                            : ATA     
  Model Number                            : HGST HUS724030AL
  Firmware Revision                       : AB70
  Serial No                               : PN2281P9J9HXRY
  GUID                                    : 5000cca248e08222
  Protocol                                : SATA
  Drive Type                              : SATA_HDD

Device is a Hard disk
  Enclosure #                             : 1
  Slot #                                  : 7
  SAS Address                             : 4433221-1-0700-0000
  State                                   : Ready (RDY)
  Size (in MB)/(in sectors)               : 2861588/5860533167
  Manufacturer                            : ATA     
  Model Number                            : HGST HUS724030AL
  Firmware Revision                       : AB70
  Serial No                               : PN2281P9JE63ZY
  GUID                                    : 5000cca248e22ed6
  Protocol                                : SATA
  Drive Type                              : SATA_HDD
------------------------------------------------------------------------
Enclosure information
------------------------------------------------------------------------
  Enclosure#                              : 1
  Logical ID                              : 56c92bf0:00173673
  Numslots                                : 8
  StartSlot                               : 0
------------------------------------------------------------------------
SAS2IRCU: Command DISPLAY Completed Successfully.
SAS2IRCU: Utility Completed Successfully.

##############################################################################


>>> lspci | grep -i lsi

[root@AbsentM ~]# lspci | grep -i lsi
01:00.0 Serial Attached SCSI controller: LSI Logic / Symbios Logic SAS2308 PCI-Express Fusion-MPT SAS-2 (rev 05)
02:00.0 Serial Attached SCSI controller: LSI Logic / Symbios Logic SAS2308 PCI-Express Fusion-MPT SAS-2 (rev 05)


##############################################################################

>>> sas2ircu list

[root@AbsentM ~]# sas2ircu list
LSI Corporation SAS2 IR Configuration Utility.
Version 20.00.00.00 (2014.09.18) 
Copyright (c) 2008-2014 LSI Corporation. All rights reserved. 


         Adapter      Vendor  Device                       SubSys  SubSys 
 Index    Type          ID      ID    Pci Address          Ven ID  Dev ID 
 -----  ------------  ------  ------  -----------------    ------  ------ 
   0     SAS2308_1     1000h    86h   00h:01h:00h:00h      1000h   0086h 

         Adapter      Vendor  Device                       SubSys  SubSys 
 Index    Type          ID      ID    Pci Address          Ven ID  Dev ID 
 -----  ------------  ------  ------  -----------------    ------  ------ 
   1     SAS2308_1     1000h    86h   00h:02h:00h:00h      1000h   0086h 
SAS2IRCU: Utility Completed Successfully.

##############################################################################

