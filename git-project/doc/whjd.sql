/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2014/6/12 20:57:45                           */
/*==============================================================*/


if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_freeaccelerationmethod"')
          and type = 'TR')
   drop trigger "CLR Trigger_freeaccelerationmethod"
go

if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_lugdownmethod"')
          and type = 'TR')
   drop trigger "CLR Trigger_lugdownmethod"
go

if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_steadystatemethod"')
          and type = 'TR')
   drop trigger "CLR Trigger_steadystatemethod"
go

if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_sysuser"')
          and type = 'TR')
   drop trigger "CLR Trigger_sysuser"
go

if exists (select 1
          from sysobjects
          where id = object_id('ti_sysuser')
          and type = 'TR')
   drop trigger ti_sysuser
go

if exists (select 1
          from sysobjects
          where id = object_id('"CLR Trigger_twospeedidlemethod"')
          and type = 'TR')
   drop trigger "CLR Trigger_twospeedidlemethod"
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('AgencyEnvironmentalLabelCollar') and o.name = 'FK_AGENCYEN_REFERENCE_SYSUSER')
alter table AgencyEnvironmentalLabelCollar
   drop constraint FK_AGENCYEN_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DetectionCommisionSheet') and o.name = 'FK_DETECTIO_REFERENCE_LUGDOWNM')
alter table DetectionCommisionSheet
   drop constraint FK_DETECTIO_REFERENCE_LUGDOWNM
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DetectionCommisionSheet') and o.name = 'FK_DETECTIO_REFERENCE_FREEACCE')
alter table DetectionCommisionSheet
   drop constraint FK_DETECTIO_REFERENCE_FREEACCE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DetectionCommisionSheet') and o.name = 'FK_DETECTIO_REFERENCE_STEADYST')
alter table DetectionCommisionSheet
   drop constraint FK_DETECTIO_REFERENCE_STEADYST
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DetectionCommisionSheet') and o.name = 'FK_DETECTIO_REFERENCE_TWOSPEED')
alter table DetectionCommisionSheet
   drop constraint FK_DETECTIO_REFERENCE_TWOSPEED
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DetectionCommisionSheet') and o.name = 'FK_DETECTIO_REFERENCE_ENVIRONM')
alter table DetectionCommisionSheet
   drop constraint FK_DETECTIO_REFERENCE_ENVIRONM
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DetectionCommisionSheet') and o.name = 'FK_DETECTIO_REFERENCE_MOTORTWO')
alter table DetectionCommisionSheet
   drop constraint FK_DETECTIO_REFERENCE_MOTORTWO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('calibrationRecord') and o.name = 'FK_CALIBRAT_REFERENCE_DETECTIO')
alter table calibrationRecord
   drop constraint FK_CALIBRAT_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('calibrationRecord') and o.name = 'FK_CALIBRAT_REFERENCE_SYSUSER')
alter table calibrationRecord
   drop constraint FK_CALIBRAT_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('detectionLine') and o.name = 'FK_DETECTIO_REFERENCE_INSPECTI')
alter table detectionLine
   drop constraint FK_DETECTIO_REFERENCE_INSPECTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('deviceInfo') and o.name = 'FK_DEVICEIN_REFERENCE_DETECTIO')
alter table deviceInfo
   drop constraint FK_DEVICEIN_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('devicePurchaseRecord') and o.name = 'FK_DEVICEPU_REFERENCE_INSPECTI')
alter table devicePurchaseRecord
   drop constraint FK_DEVICEPU_REFERENCE_INSPECTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('environmentalLabel') and o.name = 'FK_ENVIRONM_REFERENCE_INSPECTI')
alter table environmentalLabel
   drop constraint FK_ENVIRONM_REFERENCE_INSPECTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('environmentalLabelCollar') and o.name = 'FK_stationID_stationID')
alter table environmentalLabelCollar
   drop constraint FK_stationID_stationID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('environmentalLabelCollar') and o.name = 'FK_ENVIRONM_REFERENCE_SYSUSER')
alter table environmentalLabelCollar
   drop constraint FK_ENVIRONM_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('freeAccelerationMethod') and o.name = 'FK_FREEACCE_REFERENCE_DETECTIO')
alter table freeAccelerationMethod
   drop constraint FK_FREEACCE_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('freeAccelerationMethod') and o.name = 'FK_FREEACCE_REFERENCE_SYSUSER_DET')
alter table freeAccelerationMethod
   drop constraint FK_FREEACCE_REFERENCE_SYSUSER_DET
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('freeAccelerationMethod') and o.name = 'FK_FREEACCE_REFERENCE_SYSUSER_APP')
alter table freeAccelerationMethod
   drop constraint FK_FREEACCE_REFERENCE_SYSUSER_APP
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('freeAccelerationMethod') and o.name = 'FK_FREEACCE_REFERENCE_SYSUSER_CHK')
alter table freeAccelerationMethod
   drop constraint FK_FREEACCE_REFERENCE_SYSUSER_CHK
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('limitValueReference') and o.name = 'FK_LIMITVAL_REFERENCE_DETECTIO')
alter table limitValueReference
   drop constraint FK_LIMITVAL_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('lugDownMethod') and o.name = 'FK_LUGDOWNM_REFERENCE_DETECTIO')
alter table lugDownMethod
   drop constraint FK_LUGDOWNM_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('lugDownMethod') and o.name = 'FK_LUGDOWNM_REFERENCE_SYSUSER_DET')
alter table lugDownMethod
   drop constraint FK_LUGDOWNM_REFERENCE_SYSUSER_DET
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('lugDownMethod') and o.name = 'FK_LUGDOWNM_REFERENCE_SYSUSER_CHK')
alter table lugDownMethod
   drop constraint FK_LUGDOWNM_REFERENCE_SYSUSER_CHK
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('lugDownMethod') and o.name = 'FK_LUGDOWNM_REFERENCE_SYSUSER_APP')
alter table lugDownMethod
   drop constraint FK_LUGDOWNM_REFERENCE_SYSUSER_APP
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('maintainceRecord') and o.name = 'FK_MAINTAIN_REFERENCE_DEVICEIN')
alter table maintainceRecord
   drop constraint FK_MAINTAIN_REFERENCE_DEVICEIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('maintainceRecord') and o.name = 'FK_MAINTAIN_REFERENCE_SYSUSER')
alter table maintainceRecord
   drop constraint FK_MAINTAIN_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('menu') and o.name = 'FK_MENU_REFERENCE_MENU')
alter table menu
   drop constraint FK_MENU_REFERENCE_MENU
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('motorTwoSpeedIdleMethod') and o.name = 'FK_MOTORTWO_REFERENCE_DETECTIO')
alter table motorTwoSpeedIdleMethod
   drop constraint FK_MOTORTWO_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('motorTwoSpeedIdleMethod') and o.name = 'FK_MOTORTWO_REFERENCE_SYSUSER')
alter table motorTwoSpeedIdleMethod
   drop constraint FK_MOTORTWO_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('referenceMaterialsRecord') and o.name = 'FK_REFERENC_REFERENCE_INSPECTI')
alter table referenceMaterialsRecord
   drop constraint FK_REFERENC_REFERENCE_INSPECTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('rolePrivilege') and o.name = 'FK_ROLEPRIV_REFERENCE_MENU')
alter table rolePrivilege
   drop constraint FK_ROLEPRIV_REFERENCE_MENU
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('rolePrivilege') and o.name = 'FK_ROLEPRIV_REFERENCE_ROLES')
alter table rolePrivilege
   drop constraint FK_ROLEPRIV_REFERENCE_ROLES
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('stationManagement') and o.name = 'FK_STATIONM_REFERENCE_INSPECTI')
alter table stationManagement
   drop constraint FK_STATIONM_REFERENCE_INSPECTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('stationManagement') and o.name = 'FK_STATIONM_REFERENCE_SYSUSER')
alter table stationManagement
   drop constraint FK_STATIONM_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('steadyStateMethod') and o.name = 'FK_STEADYST_REFERENCE_SYSUSER_OPR')
alter table steadyStateMethod
   drop constraint FK_STEADYST_REFERENCE_SYSUSER_OPR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('steadyStateMethod') and o.name = 'FK_STEADYST_REFERENCE_DETECTIO')
alter table steadyStateMethod
   drop constraint FK_STEADYST_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('steadyStateMethod') and o.name = 'FK_STEADYST_REFERENCE_SYSUSER_DRI')
alter table steadyStateMethod
   drop constraint FK_STEADYST_REFERENCE_SYSUSER_DRI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('sysUser') and o.name = 'FK_SYSUSER_REFERENCE_INSPECTI')
alter table sysUser
   drop constraint FK_SYSUSER_REFERENCE_INSPECTI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('trainingRecord') and o.name = 'FK_TRAINING_REFERENCE_SYSUSER')
alter table trainingRecord
   drop constraint FK_TRAINING_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('twoSpeedIdleMethod') and o.name = 'FK_TWOSPEED_REFERENCE_DETECTIO')
alter table twoSpeedIdleMethod
   drop constraint FK_TWOSPEED_REFERENCE_DETECTIO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('twoSpeedIdleMethod') and o.name = 'FK_TWOSPEED_REFERENCE_SYSUSER_OPR')
alter table twoSpeedIdleMethod
   drop constraint FK_TWOSPEED_REFERENCE_SYSUSER_OPR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('twoSpeedIdleMethod') and o.name = 'FK_TWOSPEED_REFERENCE_SYSUSER_DRI')
alter table twoSpeedIdleMethod
   drop constraint FK_TWOSPEED_REFERENCE_SYSUSER_DRI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('userRole') and o.name = 'FK_USERROLE_REFERENCE_SYSUSER')
alter table userRole
   drop constraint FK_USERROLE_REFERENCE_SYSUSER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('userRole') and o.name = 'FK_USERROLE_REFERENCE_ROLES')
alter table userRole
   drop constraint FK_USERROLE_REFERENCE_ROLES
go

if exists (select 1
            from  sysobjects
           where  id = object_id('AgencyEnvironmentalLabelCollar')
            and   type = 'U')
   drop table AgencyEnvironmentalLabelCollar
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DetectionCommisionSheet')
            and   type = 'U')
   drop table DetectionCommisionSheet
go

if exists (select 1
            from  sysobjects
           where  id = object_id('InspectionStation')
            and   type = 'U')
   drop table InspectionStation
go

if exists (select 1
            from  sysobjects
           where  id = object_id('blackNameList')
            and   type = 'U')
   drop table blackNameList
go

if exists (select 1
            from  sysobjects
           where  id = object_id('calibrationRecord')
            and   type = 'U')
   drop table calibrationRecord
go

if exists (select 1
            from  sysobjects
           where  id = object_id('detectionLine')
            and   type = 'U')
   drop table detectionLine
go

if exists (select 1
            from  sysobjects
           where  id = object_id('detectionMethodReference')
            and   type = 'U')
   drop table detectionMethodReference
go

if exists (select 1
            from  sysobjects
           where  id = object_id('deviceInfo')
            and   type = 'U')
   drop table deviceInfo
go

if exists (select 1
            from  sysobjects
           where  id = object_id('devicePurchaseRecord')
            and   type = 'U')
   drop table devicePurchaseRecord
go

if exists (select 1
            from  sysobjects
           where  id = object_id('distributionTask')
            and   type = 'U')
   drop table distributionTask
go

if exists (select 1
            from  sysobjects
           where  id = object_id('environmentalLabel')
            and   type = 'U')
   drop table environmentalLabel
go

if exists (select 1
            from  sysobjects
           where  id = object_id('environmentalLabelCollar')
            and   type = 'U')
   drop table environmentalLabelCollar
go

if exists (select 1
            from  sysobjects
           where  id = object_id('freeAccelerationMethod')
            and   type = 'U')
   drop table freeAccelerationMethod
go

if exists (select 1
            from  sysobjects
           where  id = object_id('limitValueReference')
            and   type = 'U')
   drop table limitValueReference
go

if exists (select 1
            from  sysobjects
           where  id = object_id('lugDownMethod')
            and   type = 'U')
   drop table lugDownMethod
go

if exists (select 1
            from  sysobjects
           where  id = object_id('maintainceRecord')
            and   type = 'U')
   drop table maintainceRecord
go

if exists (select 1
            from  sysobjects
           where  id = object_id('menu')
            and   type = 'U')
   drop table menu
go

if exists (select 1
            from  sysobjects
           where  id = object_id('motorTwoSpeedIdleMethod')
            and   type = 'U')
   drop table motorTwoSpeedIdleMethod
go

if exists (select 1
            from  sysobjects
           where  id = object_id('referenceMaterialsRecord')
            and   type = 'U')
   drop table referenceMaterialsRecord
go

if exists (select 1
            from  sysobjects
           where  id = object_id('rolePrivilege')
            and   type = 'U')
   drop table rolePrivilege
go

if exists (select 1
            from  sysobjects
           where  id = object_id('roles')
            and   type = 'U')
   drop table roles
go

if exists (select 1
            from  sysobjects
           where  id = object_id('stationManagement')
            and   type = 'U')
   drop table stationManagement
go

if exists (select 1
            from  sysobjects
           where  id = object_id('steadyStateMethod')
            and   type = 'U')
   drop table steadyStateMethod
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sysUser')
            and   type = 'U')
   drop table sysUser
go

if exists (select 1
            from  sysobjects
           where  id = object_id('trainingRecord')
            and   type = 'U')
   drop table trainingRecord
go

if exists (select 1
            from  sysobjects
           where  id = object_id('twoSpeedIdleMethod')
            and   type = 'U')
   drop table twoSpeedIdleMethod
go

if exists (select 1
            from  sysobjects
           where  id = object_id('userRole')
            and   type = 'U')
   drop table userRole
go

if exists (select 1
            from  sysobjects
           where  id = object_id('webServiceDataInteraction')
            and   type = 'U')
   drop table webServiceDataInteraction
go

/*==============================================================*/
/* Table: AgencyEnvironmentalLabelCollar                        */
/*==============================================================*/
create table AgencyEnvironmentalLabelCollar (
   ID                   int                  not null,
   userID               int                  null,
   collarTime           datetime             null,
   Year                 int                  null,
   yellowLabelNum       int                  null,
   greenLabelNum        int                  null,
   status               int                  null,
   remarks              varchar(50)          null,
   constraint PK_AGENCYENVIRONMENTALLABELCOL primary key (ID)
)
go

/*==============================================================*/
/* Table: DetectionCommisionSheet                               */
/*==============================================================*/
create table DetectionCommisionSheet (
   id                   int                  not null,
   free_ID              int                  null,
   lug_ID               int                  null,
   ste_ID               int                  null,
   two_ID               int                  null,
   env_ID               int                  null,
   mot_ID               int                  null,
   licence              varchar(20)          null,
   vehicleOwnerName     varchar(20)          null,
   vehicleOwnerAddress  varchar(50)          null,
   detectionTime        datetime             null,
   vin                  varchar(30)          null,
   engineCode           varchar(20)          null,
   vehicleModelCode     varchar(40)          null,
   engineModel          varchar(20)          null,
   fuelType             varchar(15)          null,
   baseQuality          float                null,
   maxTotalQuality      float                null,
   vehicleLength        float                null,
   EngineemissionAmount float                null,
   totalMiles           float                null,
   vehicleRegisterDate  datetime             null,
   vehicleType          int                  null,
   labelDistributeType  int                  null,
   isCancel             int                  null,
   detectionMethod      varchar(25)          null,
   year_count           int                  null,
   commisionSheetStatus int                  null,
   emissionStandard     int                  null,
   reportNumber         varchar(20)          null,
   stationName          varchar(30)          null,
   conclusion           int                  null,
   requestStatus        int                  null,
   vechileIssueCertTime datetime             null,
   validatePeriod       datetime             null,
   licenseColor         varchar(10)          null,
   vehicleLoadNum       int                  null,
   errorReason          varchar(50)          null,
   vehicleClass         varchar(30)          null,
   receiveStatus        int                  null,
   constraint PK_DETECTIONCOMMISIONSHEET primary key (id)
)
go

/*==============================================================*/
/* Table: InspectionStation                                     */
/*==============================================================*/
create table InspectionStation (
   stationID            int                  not null,
   stationName          varchar(20)          null,
   stationAddress       varchar(40)          null,
   registrationTime     datetime             null,
   qulificationType     varchar(2)           null,
   qulificationTime     datetime             null,
   validPeriod          datetime             null,
   remarks              varchar(40)          null,
   status               int                  null,
   constraint PK_INSPECTIONSTATION primary key (stationID)
)
go

/*==============================================================*/
/* Table: blackNameList                                         */
/*==============================================================*/
create table blackNameList (
   ID                   int                  not null,
   licence              varchar(10)          null,
   violationType        varchar(50)          null,
   violationTime        datetime             null,
   violationAddress     varchar(100)         null,
   isPunished           bit                  null,
   isCancel             bit                  null,
   remarks              varchar(100)         null,
   constraint PK_BLACKNAMELIST primary key (ID)
)
go

/*==============================================================*/
/* Table: calibrationRecord                                     */
/*==============================================================*/
create table calibrationRecord (
   lineID               int                  null,
   userID               int                  null,
   calibrationTime      datetime             null,
   recordID             int                  not null,
   NOStandardValue      float                null,
   NOAfterValue         float                null,
   HCStandardValue      float                null,
   HCAfterValue         float                null,
   COStandardValue      float                null,
   COAfterValue         float                null,
   CO2StandardValue     float                null,
   CO2AfterValue        float                null,
   status               int                  null,
   constraint PK_CALIBRATIONRECORD primary key (recordID)
)
go

/*==============================================================*/
/* Table: detectionLine                                         */
/*==============================================================*/
create table detectionLine (
   lineID               int                  not null,
   lineName             varchar(20)          null,
   localeID             int                  null,
   stationID            int                  null,
   maxDetectionNum      int                  null,
   lineStatus           int                  null,
   constraint PK_DETECTIONLINE primary key (lineID)
)
go

/*==============================================================*/
/* Table: detectionMethodReference                              */
/*==============================================================*/
create table detectionMethodReference (
   ID                   int                  not null,
   fuelType             varchar(50)          null,
   lengthMin            float                null,
   weightMin            float                null,
   detectionMedhodName  varchar(50)          null,
   lengthMax            float                null,
   weightMax            float                null,
   constraint PK_DETECTIONMETHODREFERENCE primary key (ID)
)
go

/*==============================================================*/
/* Table: deviceInfo                                            */
/*==============================================================*/
create table deviceInfo (
   ID                   int                  not null,
   lineID               int                  null,
   deviceStatus         int                  null,
   remarks              varchar(40)          null,
   deviceName           varchar(20)          null,
   manufacturer         varchar(40)          null,
   deviceModel          varchar(20)          null,
   constraint PK_DEVICEINFO primary key (ID)
)
go

/*==============================================================*/
/* Table: devicePurchaseRecord                                  */
/*==============================================================*/
create table devicePurchaseRecord (
   recordID             int                  not null,
   stationID            int                  null,
   deviceName           varchar(20)          null,
   manufacturer         varchar(40)          null,
   deviceModel          varchar(20)          null,
   purchaseNum          int                  null,
   purchaseTime         datetime             null,
   remarks              varchar(50)          null,
   status               int                  null,
   specification        varchar(10)          null,
   constraint PK_DEVICEPURCHASERECORD primary key (recordID)
)
go

/*==============================================================*/
/* Table: distributionTask                                      */
/*==============================================================*/
create table distributionTask (
   id                   int                  not null,
   commisionSheetId     int                  null,
   licence              varchar(20)          null,
   vechileModel         varchar(30)          null,
   engineModel          varchar(30)          null,
   vehicleOwnerName     varchar(20)          null,
   vehicleAddress       varchar(30)          null,
   vin                  varchar(30)          null,
   vehicleRegisterDate  varchar(30)          null,
   engineCode           varchar(30)          null,
   issueDate            varchar(30)          null,
   totalQuality         float                null,
   vehicleType          varchar(20)          null,
   validatePeriod       varchar(30)          null,
   stationName          varchar(20)          null,
   remarks              varchar(30)          null,
   licenseColor         varchar(30)          null,
   vehicleLoadNum       int                  null,
   handleStatus         int                  null,
   labelDistributeType  int                  null,
   constraint PK_DISTRIBUTIONTASK primary key (id)
)
go

/*==============================================================*/
/* Table: environmentalLabel                                    */
/*==============================================================*/
create table environmentalLabel (
   ID                   int                  not null,
   labelID              varchar(50)          null,
   licence              char(10)             null,
   vehicleModelCode     varchar(50)          null,
   vehicleBrand         varchar(50)          null,
   fuelType             varchar(50)          null,
   vehicleClassification varchar(30)          null,
   vehicleOwner         varchar(20)          null,
   vehicleRegisterTime  datetime             null,
   distributionCertTime datetime             null,
   vinNo                varchar(30)          null,
   emissionStandard     varchar(30)          null,
   issueDate            datetime             null,
   validPeriod          datetime             null,
   labelType            varchar(30)          null,
   stationID            int                  null,
   year                 int                  null,
   isPrint              bit                  null,
   isCancel             bit                  null,
   stationName          varchar(20)          null,
   constraint PK_ENVIRONMENTALLABEL primary key (ID)
)
go

/*==============================================================*/
/* Table: environmentalLabelCollar                              */
/*==============================================================*/
create table environmentalLabelCollar (
   ID                   int                  not null,
   userID               int                  null,
   collarTime           datetime             null,
   Year                 int                  null,
   yellowLabelNum       int                  null,
   greenLabelNum        int                  null,
   remarks              varchar(50)          null,
   stationID            int                  null,
   status               int                  null,
   constraint PK_ENVIRONMENTALLABELCOLLAR primary key (ID)
)
go

/*==============================================================*/
/* Table: freeAccelerationMethod                                */
/*==============================================================*/
create table freeAccelerationMethod (
   ID                   int                  not null,
   lineID               int                  null,
   vehicleManufacturer  varchar(50)          null,
   FuelGrade            varchar(15)          null,
   airInletMode         varchar(10)          null,
   fuelSupplySystemModel varchar(30)          null,
   hasEmissionProcessDevice bit                  null,
   temperature          float                null,
   airPressure          float                null,
   relativeHumidity     float                null,
   rpm                  float                null,
   lastOneTest          float                null,
   lastTwoTest          float                null,
   lastThreeTest        float                null,
   average              float                null,
   ZJ_Limit             float                null,
   testDeviceModel      varchar(20)          null,
   InspectorID          int                  null,
   AccessorID           int                  null,
   ApproverID           int                  null,
   reportStatus         int                  null,
   wheelNums            int                  null,
   constraint PK_FREEACCELERATIONMETHOD primary key (ID)
)
go

/*==============================================================*/
/* Table: limitValueReference                                   */
/*==============================================================*/
create table limitValueReference (
   ID                   int                  not null,
   detectionMedhod      int                  null,
   minRegisterTime      datetime             null,
   maxRegisterTime      datetime             null,
   minBaseQuality       float                null,
   maxBaseQuality       float                null,
   airInletMode         varchar(15)          null,
   strokes              int                  null,
   wheelNums            int                  null,
   WT_HC_ASM5025        float                null,
   WT_HC_ASM2540        float                null,
   WT_CO_ASM5025        float                null,
   WT_CO_ASM2540        float                null,
   WT_NO_ASM5025        float                null,
   WT_NO_ASM2540        float                null,
   ZJ_Limit             float                null,
   SDS_lambda           float                null,
   SDS_L_CO             float                null,
   SDS_L_HC             float                null,
   SDS_H_CO             float                null,
   SDS_H_HC             float                null,
   JZ_Limit             float                null,
   JZ_MaxRpm            float                null,
   vehicleDetailType    int                  null,
   constraint PK_LIMITVALUEREFERENCE primary key (ID)
)
go

/*==============================================================*/
/* Table: lugDownMethod                                         */
/*==============================================================*/
create table lugDownMethod (
   ID                   int                  not null,
   lineID               int                  null,
   vehicleManufacturer  varchar(30)          null,
   fuelGrade            varchar(15)          null,
   airInletMode         varchar(10)          null,
   fuelSupplySystemModel varchar(30)          null,
   emissionProcessDevice bit                  null,
   temperature          float                null,
   airPressure          float                null,
   relativeHumidity     float                null,
   hundredPoint         float                null,
   nintyPoint           float                null,
   eightyPoint          float                null,
   KM_Limit             float                null,
   KW_Limit             float                null,
   KW_Result            float                null,
   testDeviceModel      varchar(20)          null,
   InspectorID          int                  null,
   AccessorID           int                  null,
   ApproverID           int                  null,
   reportStatus         int                  null,
   wheelNums            int                  null,
   constraint PK_LUGDOWNMETHOD primary key (ID)
)
go

/*==============================================================*/
/* Table: maintainceRecord                                      */
/*==============================================================*/
create table maintainceRecord (
   recordID             int                  not null,
   deviceID             int                  null,
   userID               int                  null,
   maintainceTime       datetime             null,
   isNormal             bit                  null,
   measures             varchar(30)          null,
   maintainceOutcome    varchar(30)          null,
   status               int                  null,
   constraint PK_MAINTAINCERECORD primary key (recordID)
)
go

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu (
   Id                   varchar(50)          not null,
   iconCls              varchar(50)          null,
   text                 varchar(50)          null,
   url                  varchar(200)         null,
   admin                int                  null,
   pid                  varchar(50)          null,
   seq                  int                  null,
   constraint PK_MENU primary key (Id)
)
go

/*==============================================================*/
/* Table: motorTwoSpeedIdleMethod                               */
/*==============================================================*/
create table motorTwoSpeedIdleMethod (
   ID                   int                  not null,
   lineID               int                  null,
   userID               int                  null,
   vehicleManufacturer  varchar(50)          null,
   strokes              int                  null,
   maxRpm               float                null,
   idleSpeedRpm         float                null,
   highIdleSpeedRpm     float                null,
   fuelSpecification    varchar(30)          null,
   lubeSpecification    varchar(30)          null,
   fuelSupplyStyle      varchar(15)          null,
   fuelJetSystem        varchar(20)          null,
   pollutionControlDevice varchar(50)          null,
   exhaustAnalyzerModel varchar(20)          null,
   tachometerModel      varchar(20)          null,
   airPressure          float                null,
   temperature          float                null,
   humidity             float                null,
   experimentAddress    varchar(40)          null,
   experimentTime       datetime             null,
   H_COResult           float                null,
   H_CO2Result          float                null,
   H_HCResult           float                null,
   COResult             float                null,
   CO2Result            float                null,
   HCResult             float                null,
   H_COReviseResult     float                null,
   COReviseResult       float                null,
   H_CORoundResult      float                null,
   H_CO2RoundResult     float                null,
   H_HCRoundResult      float                null,
   CORoundResult        float                null,
   CO2RoundResult       float                null,
   HCRoundResult        float                null,
   reportStatus         int                  null,
   COLimit              float                null,
   HCLimit              float                null,
   H_COLimit            float                null,
   H_HCLimit            float                null,
   wheelNums            int                  null,
   constraint PK_MOTORTWOSPEEDIDLEMETHOD primary key (ID)
)
go

/*==============================================================*/
/* Table: referenceMaterialsRecord                              */
/*==============================================================*/
create table referenceMaterialsRecord (
   recordID             int                  not null,
   materialName         varchar(50)          null,
   manufacturer         varchar(50)          null,
   specification        varchar(50)          null,
   purchaseNum          int                  null,
   purchaseTime         datetime             null,
   remarks              varchar(50)          null,
   purchaseStation      int                  null,
   status               int                  null,
   model                varchar(30)          null,
   constraint PK_REFERENCEMATERIALSRECORD primary key (recordID)
)
go

/*==============================================================*/
/* Table: rolePrivilege                                         */
/*==============================================================*/
create table rolePrivilege (
   ID                   int                  not null,
   roleID               int                  null,
   menuID               varchar(50)          null,
   relationStatus       int                  null,
   constraint PK_ROLEPRIVILEGE primary key (ID)
)
go

/*==============================================================*/
/* Table: roles                                                 */
/*==============================================================*/
create table roles (
   roleID               int                  not null,
   roleName             varchar(50)          null,
   roleDescription      varchar(50)          null,
   roleStatus           int                  null,
   constraint PK_ROLES primary key (roleID)
)
go

/*==============================================================*/
/* Table: stationManagement                                     */
/*==============================================================*/
create table stationManagement (
   id                   int                  not null,
   Ins_stationID        int                  null,
   userID               int                  null,
   status               int                  null,
   remark               varchar(40)          null,
   constraint PK_STATIONMANAGEMENT primary key (id)
)
go

/*==============================================================*/
/* Table: steadyStateMethod                                     */
/*==============================================================*/
create table steadyStateMethod (
   ID                   int                  not null,
   lineID               int                  null,
   vehicleManufacturer  varchar(50)          null,
   engineManufacturer   varchar(40)          null,
   fuelSpecification    varchar(15)          null,
   singleAxleLoad       float                null,
   chassisModel         varchar(30)          null,
   driveMode            varchar(20)          null,
   tirePressure         float                null,
   transmissionForm     varchar(30)          null,
   gearNumber           int                  null,
   cylinderNumber       int                  null,
   catalyticConverter   varchar(40)          null,
   deviceAuthNumber     varchar(30)          null,
   deviceName           varchar(30)          null,
   deviceModel          varchar(30)          null,
   deviceManufacturer   varchar(30)          null,
   chassisDynamometer   varchar(30)          null,
   exhaustGasAnalyser   varchar(30)          null,
   temperature          float                null,
   airPressure          float                null,
   relativeHumidity     float                null,
   WT_HC_ASM5025        float                null,
   WT_HC_ASM2540        float                null,
   WT_CO_ASM5025        float                null,
   WT_CO_ASM2540        float                null,
   WT_NO_ASM5025        float                null,
   WT_NO_ASM2540        float                null,
   WT_HC_ASM5025_Limit  float                null,
   WT_HC_ASM2540_Limit  float                null,
   WT_CO_ASM5025_Limit  float                null,
   WT_CO_ASM2540_Limit  float                null,
   WT_NO_ASM5025_Limit  float                null,
   WT_NO_ASM2540_Limit  float                null,
   WT_HC_ASM5025_Conclusion bit                  null,
   WT_HC_ASM2540_Conclusion bit                  null,
   WT_CO_ASM5025_Conclusion bit                  null,
   WT_CO_ASM2540_Conclusion bit                  null,
   WT_NO_ASM5025_Conclusion bit                  null,
   WT_NO_ASM2540_Conclusion bit                  null,
   WT_HC_ASM5025_Judge  bit                  null,
   WT_HC_ASM2540_Judge  bit                  null,
   WT_CO_ASM5025_Judge  bit                  null,
   WT_CO_ASM2540_Judge  bit                  null,
   WT_NO_ASM5025_Judge  bit                  null,
   WT_NO_ASM2540_Judge  bit                  null,
   InspecOperatorID     int                  null,
   InspecDriverID       int                  null,
   reportStatus         int                  null,
   wheelNums            int                  null,
   constraint PK_STEADYSTATEMETHOD primary key (ID)
)
go

/*==============================================================*/
/* Table: sysUser                                               */
/*==============================================================*/
create table sysUser (
   userID               int                  not null,
   stationID            int                  null,
   userName             varchar(20)          null,
   simplifyName         varchar(20)          null,
   IDCard               varchar(20)          null,
   sex                  varchar(2)           null,
   jobTitle             varchar(20)          null,
   tel                  varchar(15)          null,
   certificateName      varchar(30)          null,
   certificateTime      datetime             null,
   degree               varchar(15)          null,
   status               int                  null,
   password             varchar(20)          null,
   constraint PK_SYSUSER primary key (userID)
)
go

/*==============================================================*/
/* Table: trainingRecord                                        */
/*==============================================================*/
create table trainingRecord (
   recordID             int                  not null,
   userID               int                  null,
   trainingTime         datetime             null,
   trainingAddress      varchar(50)          null,
   trainingType         int                  null,
   trainingDetail       varchar(50)          null,
   status               int                  null,
   constraint PK_TRAININGRECORD primary key (recordID)
)
go

/*==============================================================*/
/* Table: twoSpeedIdleMethod                                    */
/*==============================================================*/
create table twoSpeedIdleMethod (
   ID                   int                  not null,
   lineID               int                  null,
   vehicleManufacturer  varchar(30)          null,
   engineManufacturer   varchar(40)          null,
   fuelSpecification    varchar(15)          null,
   singleAxleLoad       float                null,
   chassisModel         varchar(30)          null,
   driveMode            varchar(20)          null,
   tirePressure         float                null,
   transmissionForm     varchar(30)          null,
   gearNumber           int                  null,
   cylinderNumber       int                  null,
   catalyticConverter   varchar(40)          null,
   deviceAuthNumber     varchar(30)          null,
   deviceName           varchar(30)          null,
   deviceModel          varchar(30)          null,
   deviceManufacturer   varchar(30)          null,
   temperature          float                null,
   airPressure          float                null,
   relativeHumidity     float                null,
   SDS_lambda           float                null,
   SDS_L_CO             float                null,
   SDS_L_HC             float                null,
   SDS_H_CO             float                null,
   SDS_H_HC             float                null,
   SDS_lambda_Limit     float                null,
   SDS_L_CO_Limit       float                null,
   SDS_L_HC_Limit       float                null,
   SDS_H_CO_Limit       float                null,
   SDS_H_HC_Limit       float                null,
   SDS_lambda_Conclusion bit                  null,
   SDS_L_Conclusion     bit                  null,
   SDS_H_Conclusion     bit                  null,
   InspecOperatorID     int                  null,
   InspecDriverID       int                  null,
   reportStatus         int                  null,
   wheelNums            int                  null,
   constraint PK_TWOSPEEDIDLEMETHOD primary key (ID)
)
go

/*==============================================================*/
/* Table: userRole                                              */
/*==============================================================*/
create table userRole (
   ID                   int                  not null,
   userID               int                  null,
   roleID               int                  null,
   relationStatus       int                  null,
   constraint PK_USERROLE primary key (ID)
)
go

/*==============================================================*/
/* Table: webServiceDataInteraction                             */
/*==============================================================*/
create table webServiceDataInteraction (
   id                   int                  not null,
   csId                 int                  null,
   reportId             int                  null,
   methodName           varchar(20)          null,
   stationId            int                  null,
   lineLocalId          int                  null,
   status               int                  null,
   licence              varchar(20)          null,
   constraint PK_WEBSERVICEDATAINTERACTION primary key (id)
)
go

alter table AgencyEnvironmentalLabelCollar
   add constraint FK_AGENCYEN_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade on delete set null
go

alter table DetectionCommisionSheet
   add constraint FK_DETECTIO_REFERENCE_LUGDOWNM foreign key (lug_ID)
      references lugDownMethod (ID)
go

alter table DetectionCommisionSheet
   add constraint FK_DETECTIO_REFERENCE_FREEACCE foreign key (free_ID)
      references freeAccelerationMethod (ID)
go

alter table DetectionCommisionSheet
   add constraint FK_DETECTIO_REFERENCE_STEADYST foreign key (ste_ID)
      references steadyStateMethod (ID)
go

alter table DetectionCommisionSheet
   add constraint FK_DETECTIO_REFERENCE_TWOSPEED foreign key (two_ID)
      references twoSpeedIdleMethod (ID)
go

alter table DetectionCommisionSheet
   add constraint FK_DETECTIO_REFERENCE_ENVIRONM foreign key (env_ID)
      references environmentalLabel (ID)
go

alter table DetectionCommisionSheet
   add constraint FK_DETECTIO_REFERENCE_MOTORTWO foreign key (mot_ID)
      references motorTwoSpeedIdleMethod (ID)
go

alter table calibrationRecord
   add constraint FK_CALIBRAT_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table calibrationRecord
   add constraint FK_CALIBRAT_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade on delete set null
go

alter table detectionLine
   add constraint FK_DETECTIO_REFERENCE_INSPECTI foreign key (stationID)
      references InspectionStation (stationID)
         on update cascade on delete set null
go

alter table deviceInfo
   add constraint FK_DEVICEIN_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table devicePurchaseRecord
   add constraint FK_DEVICEPU_REFERENCE_INSPECTI foreign key (stationID)
      references InspectionStation (stationID)
         on update cascade on delete set null
go

alter table environmentalLabel
   add constraint FK_ENVIRONM_REFERENCE_INSPECTI foreign key (stationID)
      references InspectionStation (stationID)
         on update cascade on delete set null
go

alter table environmentalLabelCollar
   add constraint FK_stationID_stationID foreign key (stationID)
      references InspectionStation (stationID)
         on update cascade on delete set null
go

alter table environmentalLabelCollar
   add constraint FK_ENVIRONM_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade on delete set null
go

alter table freeAccelerationMethod
   add constraint FK_FREEACCE_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table freeAccelerationMethod
   add constraint FK_FREEACCE_REFERENCE_SYSUSER_DET foreign key (InspectorID)
      references sysUser (userID)
go

alter table freeAccelerationMethod
   add constraint FK_FREEACCE_REFERENCE_SYSUSER_APP foreign key (ApproverID)
      references sysUser (userID)
go

alter table freeAccelerationMethod
   add constraint FK_FREEACCE_REFERENCE_SYSUSER_CHK foreign key (AccessorID)
      references sysUser (userID)
go

alter table limitValueReference
   add constraint FK_LIMITVAL_REFERENCE_DETECTIO foreign key (detectionMedhod)
      references detectionMethodReference (ID)
         on update cascade on delete cascade
go

alter table lugDownMethod
   add constraint FK_LUGDOWNM_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table lugDownMethod
   add constraint FK_LUGDOWNM_REFERENCE_SYSUSER_DET foreign key (InspectorID)
      references sysUser (userID)
go

alter table lugDownMethod
   add constraint FK_LUGDOWNM_REFERENCE_SYSUSER_CHK foreign key (AccessorID)
      references sysUser (userID)
go

alter table lugDownMethod
   add constraint FK_LUGDOWNM_REFERENCE_SYSUSER_APP foreign key (ApproverID)
      references sysUser (userID)
go

alter table maintainceRecord
   add constraint FK_MAINTAIN_REFERENCE_DEVICEIN foreign key (deviceID)
      references deviceInfo (ID)
         on update cascade on delete set null
go

alter table maintainceRecord
   add constraint FK_MAINTAIN_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade on delete set null
go

alter table menu
   add constraint FK_MENU_REFERENCE_MENU foreign key (pid)
      references menu (Id)
go

alter table motorTwoSpeedIdleMethod
   add constraint FK_MOTORTWO_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table motorTwoSpeedIdleMethod
   add constraint FK_MOTORTWO_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
go

alter table referenceMaterialsRecord
   add constraint FK_REFERENC_REFERENCE_INSPECTI foreign key (purchaseStation)
      references InspectionStation (stationID)
go

alter table rolePrivilege
   add constraint FK_ROLEPRIV_REFERENCE_MENU foreign key (menuID)
      references menu (Id)
         on update cascade on delete cascade
go

alter table rolePrivilege
   add constraint FK_ROLEPRIV_REFERENCE_ROLES foreign key (roleID)
      references roles (roleID)
         on update cascade on delete set null
go

alter table stationManagement
   add constraint FK_STATIONM_REFERENCE_INSPECTI foreign key (Ins_stationID)
      references InspectionStation (stationID)
         on update cascade on delete set null
go

alter table stationManagement
   add constraint FK_STATIONM_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade on delete set null
go

alter table steadyStateMethod
   add constraint FK_STEADYST_REFERENCE_SYSUSER_OPR foreign key (InspecOperatorID)
      references sysUser (userID)
go

alter table steadyStateMethod
   add constraint FK_STEADYST_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table steadyStateMethod
   add constraint FK_STEADYST_REFERENCE_SYSUSER_DRI foreign key (InspecDriverID)
      references sysUser (userID)
go

alter table sysUser
   add constraint FK_SYSUSER_REFERENCE_INSPECTI foreign key (stationID)
      references InspectionStation (stationID)
go

alter table trainingRecord
   add constraint FK_TRAINING_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade
go

alter table twoSpeedIdleMethod
   add constraint FK_TWOSPEED_REFERENCE_DETECTIO foreign key (lineID)
      references detectionLine (lineID)
         on update cascade on delete set null
go

alter table twoSpeedIdleMethod
   add constraint FK_TWOSPEED_REFERENCE_SYSUSER_OPR foreign key (InspecOperatorID)
      references sysUser (userID)
go

alter table twoSpeedIdleMethod
   add constraint FK_TWOSPEED_REFERENCE_SYSUSER_DRI foreign key (InspecDriverID)
      references sysUser (userID)
go

alter table userRole
   add constraint FK_USERROLE_REFERENCE_SYSUSER foreign key (userID)
      references sysUser (userID)
         on update cascade on delete cascade
go

alter table userRole
   add constraint FK_USERROLE_REFERENCE_ROLES foreign key (roleID)
      references roles (roleID)
         on update cascade on delete set null
go


create trigger "CLR Trigger_freeaccelerationmethod" on freeAccelerationMethod  insert as
external name %Assembly.GeneratedName%.
go


create trigger "CLR Trigger_lugdownmethod" on lugDownMethod  insert as
external name %Assembly.GeneratedName%.
go


create trigger "CLR Trigger_steadystatemethod" on steadyStateMethod  insert as
external name %Assembly.GeneratedName%.
go


create trigger "CLR Trigger_sysuser" on sysUser  insert as
external name %Assembly.GeneratedName%.
go


create trigger ti_sysuser on sysUser for insert as
begin
    declare
       @maxcard  int,
       @numrows  int,
       @numnull  int,
       @errno    int,
       @errmsg   varchar(255)

    select  @numrows = @@rowcount
    if @numrows = 0
       return


    return

/*  Errors handling  */
error:
    raiserror @errno @errmsg
    rollback  transaction
end
go


create trigger "CLR Trigger_twospeedidlemethod" on twoSpeedIdleMethod  insert as
external name %Assembly.GeneratedName%.
go

