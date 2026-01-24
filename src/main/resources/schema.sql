-- 壽險保單檔
DROP TABLE IF EXISTS polf;
CREATE TABLE IF NOT EXISTS polf (
    policy_no           VARCHAR(12),        -- 保單號碼
    po_sts_code         VARCHAR(2),         -- 保單狀態
    po_issue_date       VARCHAR(9),         -- 保單生效日
    currency            VARCHAR(3),         -- 幣別
    basic_plan_code     VARCHAR(8),         -- 主約險種代碼
    basic_rate_scale    VARCHAR(1)          -- 主約險種版數
);

CREATE UNIQUE INDEX index_1 ON polf(policy_no);

INSERT INTO polf VALUES ('100000411962','44','110/11/26','TWD','20PWL','6');

-- 壽險保單暫存檔
DROP TABLE IF EXISTS rspo;
CREATE TABLE IF NOT EXISTS rspo (
    policy_no           VARCHAR(12),        -- 保單號碼
    receive_no          VARCHAR(8),         -- 受理號碼
    po_sts_code         VARCHAR(2),         -- 保單狀態
    po_issue_date       VARCHAR(9),         -- 保單生效日
    currency            VARCHAR(3),         -- 幣別
    basic_plan_code     VARCHAR(8),         -- 主約險種代碼
    basic_rate_scale    VARCHAR(1)          -- 主約險種版數
);

CREATE UNIQUE INDEX index_1 ON rspo(policy_no);

INSERT INTO rspo VALUES ('100000411962','P000001','42','110/11/26','TWD','20PWL','6');

-- 壽險保障檔
DROP TABLE IF EXISTS colf;
CREATE TABLE IF NOT EXISTS colf (
    policy_no           VARCHAR(12),        -- 保單號碼
    coverage_no         INTEGER,            -- 保障序號
    plan_code           VARCHAR(8),         -- 險種代碼
    rate_scale          VARCHAR(1),         -- 險種版數
    co_sts_code         VARCHAR(2),         -- 保障狀態
    co_issue_date       VARCHAR(9),         -- 保障生效日
    face_amt            FLOAT,              -- 保額
    client_ident        VARCHAR(2)          -- 關係碼
);

CREATE UNIQUE INDEX index_1 ON colf(policy_no, coverage_no);

INSERT INTO colf VALUES ('100000411962',1,'20PWL','6','42','110/11/26',100000.0,'I1');
INSERT INTO colf VALUES ('100000411962',2,'ADDR','I','42','110/11/26',1000000.0,'I1');
INSERT INTO colf VALUES ('100000411962',3,'AMRR','I','42','110/11/26',30000.0,'I1');
INSERT INTO colf VALUES ('100000411962',4,'DHIR','I','42','110/11/26',1000.0,'I1');
INSERT INTO colf VALUES ('100000411962',5,'BHSRB','0','42','110/11/26',1.0,'I1');
INSERT INTO colf VALUES ('100000411962',6,'20HKCRA','3','42','110/11/26',2.0,'I1');
INSERT INTO colf VALUES ('100000411962',7,'20CHHIR','0','42','110/11/26',1000.0,'I1');
INSERT INTO colf VALUES ('100000411962',8,'20CWPR','A','42','110/11/26',50000.0,'I1');

-- 壽險保障暫存檔
DROP TABLE IF EXISTS rsco;
CREATE TABLE IF NOT EXISTS rsco (
    policy_no           VARCHAR(12),        -- 保單號碼
    coverage_no         INTEGER,            -- 保障序號
    receive_no          VARCHAR(8),         -- 受理號碼
    function_ind        VARCHAR(1),         -- 功能碼
    plan_code           VARCHAR(8),         -- 險種代碼
    rate_scale          VARCHAR(1),         -- 險種版數
    co_sts_code         VARCHAR(2),         -- 保障狀態
    co_issue_date       VARCHAR(9),         -- 保障生效日
    face_amt            FLOAT,              -- 保額
    client_ident        VARCHAR(2)          -- 關係碼
);

CREATE UNIQUE INDEX index_1 ON rsco(policy_no, coverage_no);

INSERT INTO rsco VALUES ('100000411962',1,'P000001','M','20PWL','6','42','110/11/26',100000.0,'I1');
INSERT INTO rsco VALUES ('100000411962',2,'P000001','M','ADDR','I','42','110/11/26',1000000.0,'I1');
INSERT INTO rsco VALUES ('100000411962',3,'P000001','M','AMRR','I','42','110/11/26',30000.0,'I1');
INSERT INTO rsco VALUES ('100000411962',4,'P000001','M','DHIR','I','42','110/11/26',1000.0,'I1');
INSERT INTO rsco VALUES ('100000411962',5,'P000001','M','BHSRB','0','42','110/11/26',1.0,'I1');
INSERT INTO rsco VALUES ('100000411962',6,'P000001','M','20HKCRA','3','42','110/11/26',2.0,'I1');
INSERT INTO rsco VALUES ('100000411962',7,'P000001','M','20CHHIR','0','42','110/11/26',1000.0,'I1');
INSERT INTO rsco VALUES ('100000411962',8,'P000001','M','20CWPR','A','42','110/11/26',50000.0,'I1');

-- 客戶資料檔
DROP TABLE IF EXISTS clnt;
CREATE TABLE IF NOT EXISTS clnt (
    client_id       VARCHAR(10),        -- 客戶證號
    names           VARCHAR(40),        -- 姓名
    birth_date      VARCHAR(9),         -- 生日
    sex             VARCHAR(1)          -- 性別
);

CREATE UNIQUE INDEX index_1 ON clnt(client_id);

INSERT INTO clnt VALUES ('F126437610','陳Ｏ榮','070/12/28','1');
INSERT INTO clnt VALUES ('F138244321','陳Ｏ睿','101/12/14','1');
INSERT INTO clnt VALUES ('F233828687','陳Ｏ希','104/08/06','2');
INSERT INTO clnt VALUES ('Q228076928','顏Ｏ玲','072/12/20','2');

-- 客戶資料暫存檔
DROP TABLE IF EXISTS rscl;
CREATE TABLE IF NOT EXISTS rscl (
    client_id       VARCHAR(10),        -- 客戶證號
    receive_no      VARCHAR(8),         -- 受理號碼
    function_ind    VARCHAR(1),         -- 功能碼
    client_ident    VARCHAR(2),         -- 關係碼
    names           VARCHAR(40),        -- 姓名
    birth_date      VARCHAR(9),         -- 生日
    sex             VARCHAR(1)          -- 性別
);

CREATE UNIQUE INDEX index_1 ON rscl(client_id, client_ident);

INSERT INTO rscl VALUES ('F126437610','P000001','M','I1','陳Ｏ榮','070/12/28','1');
INSERT INTO rscl VALUES ('F126437610','P000001','M','O1','陳Ｏ榮','070/12/28','1');
INSERT INTO rscl VALUES ('F138244321','P000001','M','C1','陳Ｏ睿','101/12/14','1');
INSERT INTO rscl VALUES ('F233828687','P000001','M','C2','陳Ｏ希','104/08/06','2');
INSERT INTO rscl VALUES ('Q228076928','P000001','M','SS','顏Ｏ玲','072/12/20','2');

-- 客戶地址檔
DROP TABLE IF EXISTS addr;
CREATE TABLE IF NOT EXISTS addr (
    client_id       VARCHAR(10),        -- 客戶證號
    addr_ind        VARCHAR(1),         -- 地址指示
    address         VARCHAR(72),        -- 地址
    tel_1           VARCHAR(10)         -- 電話
);

CREATE UNIQUE INDEX index_1 ON addr(client_id, addr_ind);

INSERT INTO addr VALUES ('F126437610','0','新北市三重區成功路３２號２號１樓','02-29747xxx');
INSERT INTO addr VALUES ('F126437610','E','abc123456@gmail.com.tw','0928573xxx ');
INSERT INTO addr VALUES ('F138244321','0','新北市三重區成功路２號１樓','02-29747xxx');
INSERT INTO addr VALUES ('F233828687','0','新北市三重區成功路２號１樓','02-29747xxx');
INSERT INTO addr VALUES ('Q228076928','0','新北市三重區成功路３２號１樓','02-29747xxx');
INSERT INTO addr VALUES ('Q228076928','1','新北市三重區成功路３２號５樓','02-29747xxx');
INSERT INTO addr VALUES ('Q228076928','E','abc123456@gmail.com.tw','0976062xxx');

-- 客戶地址檔
DROP TABLE IF EXISTS rsdr;
CREATE TABLE IF NOT EXISTS rsdr (
    client_id       VARCHAR(10),        -- 客戶證號
    addr_ind        VARCHAR(1),         -- 地址指示
    receive_no      VARCHAR(8),         -- 受理號碼
    function_ind    VARCHAR(1),         -- 功能碼
    address         VARCHAR(72),        -- 地址
    tel_1           VARCHAR(10)         -- 電話
);

CREATE UNIQUE INDEX index_1 ON rsdr(client_id, addr_ind);

INSERT INTO rsdr VALUES ('F126437610','0','P000001','M','新北市三重區成功路３２號２號１樓','02-29747xxx');
INSERT INTO rsdr VALUES ('F126437610','E','P000001','M','cde2255@gmailＯＯＯＯ','0928573xxx ');
INSERT INTO rsdr VALUES ('F138244321','0','P000001','M','新北市三重區成功路３２號２號１樓','02-29747xxx');
INSERT INTO rsdr VALUES ('F233828687','0','P000001','M','新北市三重區成功路３２號２號１樓','02-29747xxx');
INSERT INTO rsdr VALUES ('Q228076928','0','P000001','M','新北市三重區成功路３２號２號１樓','02-29747xxx');
INSERT INTO rsdr VALUES ('Q228076928','1','P000001','M','新北市三重區成功路３２號２號５樓','02-29747xxx');
INSERT INTO rsdr VALUES ('Q228076928','E','P000001','M','cde2255@gmailＯＯＯＯ','0976062xxx ');

-- 保單關係檔
DROP TABLE IF EXISTS pocl;
CREATE TABLE IF NOT EXISTS pocl (
    policy_no       VARCHAR(12),        -- 保單號碼
    client_ident    VARCHAR(2),         -- 關係碼
    client_id       VARCHAR(10)         -- 客戶證號
);

CREATE UNIQUE INDEX index_1 ON pocl(policy_no, client_ident);

INSERT INTO pocl VALUES ('100000411962','O1','F126437610');
INSERT INTO pocl VALUES ('100000411962','I1','F126437610');
INSERT INTO pocl VALUES ('100000411962','SS','Q228076928');
INSERT INTO pocl VALUES ('100000411962','C1','F138244321');
INSERT INTO pocl VALUES ('100000411962','C2','F233828687');

-- 契變狀態表
DROP TABLE IF EXISTS chsw;
CREATE TABLE IF NOT EXISTS chsw (
    policy_no       VARCHAR(12),        -- 保單號碼
    receive_no      VARCHAR(8),         -- 受理號碼
    receive_date    VARCHAR(9),         -- 受理日期
    change_date     VARCHAR(9),         -- 變更生效日
    change_type     VARCHAR(1),         -- 變更選項 : 0.首期契變 / 1.一般契變 / 2.復效
    basic_ind       VARCHAR(1),         -- 基本變更 : Y.有 / N.無
    client_ind      VARCHAR(1),         -- 關係人變更 : Y.有 / N.無
    coverage_ind    VARCHAR(1)          -- 保障變更 : Y.有 / N.無
);

CREATE UNIQUE INDEX index_1 ON chsw(policy_no, receive_no);

INSERT INTO chsw VALUES ('100000411962', 'P000001', '115/01/10', '115/01/10', '1', 'Y', 'Y', 'Y');

-- 核保訊息表
DROP TABLE IF EXISTS psec;
CREATE TABLE IF NOT EXISTS psec (
    nb_err_code     VARCHAR(4),         -- 核保訊息代碼
    nb_err_desc     VARCHAR(240),       -- 核保訊息文字
    severity           VARCHAR(1)       -- 核保等級
);

CREATE UNIQUE INDEX index_1 ON psec(nb_err_code);

INSERT INTO psec VALUES ('P001', '保額超過可投保上限', '1');

-- 核保規則表
DROP TABLE IF EXISTS pos_rule;
CREATE TABLE IF NOT EXISTS pos_rule (
    id                  BIGSERIAL,  	            -- 流水號
    nb_err_code         VARCHAR(4) NOT NULL,    	-- 核保訊息代碼，例如 P001、UA02、PT01 等
    group_code          VARCHAR(10) DEFAULT '1',    -- 檢核群組編號
											    	-- 用途：標記「屬於同一類檢核」，相同群組編號，代表 要一起判斷(且)的規則
    rule_model          VARCHAR(1) NOT NULL,   	    -- 規則模型： 1.基本模組 / 2.保障模組 / 3.客戶模組 / 4.受益人模組 / 5.投資模組
    expression          VARCHAR(250) NOT NULL,  	-- 檢核規則
    active_flag         CHAR(1) DEFAULT 'Y'     	-- 效性：Y=啟用, N=停用
);

CREATE UNIQUE INDEX index_1 ON pos_rule(id);

INSERT INTO pos_rule VALUES (DEFAULT, 'P001', 'A', '1', '#chsw.changeType == ''1'' and #rspo.poStsCode == ''42''', 'Y');
INSERT INTO pos_rule VALUES (DEFAULT, 'P001', 'A', '2', '#rsco.functionInd.matches(''[AM]'') and #P001.contains(#pldfNew.planAbbrCode) and #rsco.faceAmt >= 10000', 'Y');
INSERT INTO pos_rule VALUES (DEFAULT, 'P001', 'B', '1', '#chsw.changeType == ''2'' and #rspo.poStsCode == ''42''', 'Y');
INSERT INTO pos_rule VALUES (DEFAULT, 'P001', 'B', '2', '#rsco.functionInd.matches(''[AM]'') and #P001.contains(#pldfNew.planAbbrCode) and #rsco.faceAmt >= 50000', 'Y');

-- 核保變數表
DROP TABLE IF EXISTS pos_variable;
CREATE TABLE IF NOT EXISTS pos_variable (
    variable_code   VARCHAR(100) NOT NULL,           -- 變數代碼
    description     VARCHAR(250) NOT NULL,                  -- 變數說明（中文）
    data_type       VARCHAR(100) NOT NULL,                  -- 變數型態：String / Integer / Double
);

CREATE UNIQUE INDEX index_1 ON pos_variable(variable_code);

INSERT INTO pos_variable VALUES ('P001', '核保訊息 P001 的 檢核險種', 'List<String>');

-- 核保訊息表
DROP TABLE IF EXISTS pos_rule_message;
CREATE TABLE IF NOT EXISTS pos_rule_message (
    nb_err_code         VARCHAR(4) NOT NULL,    	-- 核保訊息代碼，例如 P001、UA02、PT01 等
    message_type        VARCHAR(1),                 -- 訊息類型: 1.簡單 / 2.複雜
    message_template    VARCHAR(250)                -- 訊息模板
);

CREATE UNIQUE INDEX index_1 ON pos_rule_message(nb_err_code);

INSERT INTO pos_rule_message VALUES ('P001', '2', '#rsco.planCode + '','' + #rsco.clientIdent + '','' + #nbErrDesc');

-- 特殊代碼檔
DROP TABLE IF EXISTS pseb;
CREATE TABLE IF NOT EXISTS pseb (
    pseb_type   VARCHAR(10),        -- 類型
    pseb_code1  VARCHAR(50),        -- 代碼1
    pseb_code2  VARCHAR(50),        -- 代碼2
    pseb_desc1  VARCHAR(80),        -- 字串資料1
    pseb_desc2  VARCHAR(80),        -- 字串資料2
    pseb_desc3  VARCHAR(80),        -- 字串資料3
    pseb_desc4  VARCHAR(80),        -- 字串資料4
    pseb_desc5  VARCHAR(80)         -- 字串資料5
);

CREATE UNIQUE INDEX index_1 ON pseb(pseb_type, pseb_code1, pseb_code2, pseb_desc1, pseb_desc2, pseb_desc3, pseb_desc4, pseb_desc5);

INSERT INTO pseb VALUES ('PS', 'change', '', 'P001', 'ADDR', '', '', '');
INSERT INTO pseb VALUES ('PS', 'change', '', 'P001', 'AMRR', '', '', '');

-- 商品檔
DROP TABLE IF EXISTS pldf;
CREATE TABLE IF NOT EXISTS pldf (
    plan_code       VARCHAR(8),     -- 險種代碼
    rate_scale      VARCHAR(1),     -- 險種版數
    plan_abbr_code  VARCHAR(6),     -- 險種簡稱
    plan_class_code VARCHAR(4)      -- 險種類別代碼
);

CREATE UNIQUE INDEX index_1 ON pldf(plan_code, rate_scale);

INSERT INTO pldf VALUES ('20PWL','6','PWL','OH11');
INSERT INTO pldf VALUES ('ADDR','I','ADDR','6021');
INSERT INTO pldf VALUES ('AMRR','I','AMRR','6025');
INSERT INTO pldf VALUES ('DHIR','I','DHIR','6023');
INSERT INTO pldf VALUES ('BHSRB','0','BHSR','0621');
INSERT INTO pldf VALUES ('20HKCRA','3','HKCR','CA21');
INSERT INTO pldf VALUES ('20CHHIR','0','CHHIR','9W21');
INSERT INTO pldf VALUES ('20CWPR','A','CWPR','E421');

