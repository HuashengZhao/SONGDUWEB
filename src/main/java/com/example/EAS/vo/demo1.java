package com.example.EAS.vo;

/**
 * @PackageName:com.example.EAS.vo
 * @ClassName:demo1
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2021/3/12 11:09
 */
public class demo1 {
    mod.definePage("cp/bc/js/BizAccountOutBill_Edit",function(waf,bizCollBillBase,promptExpenseType,websocket_eas){
        var _public = {},_private = {},me=_self;
        //@beginPublic
        waf.extend(_public,{
                //控件事件描述:表头-申请人值改变事件
                applierChange:function(event,value){
            return _private.applierChange(event,value);
        }
	    ,
        //控件事件描述:表头-日期改变事件
        bizReqDateChange:function(changeValue,inst){
            return _private.bizReqDateChange(changeValue,inst);
        }
	    ,
        //控件事件描述:表头-部门值改变事件
        orgUnitChange:function(event,value){
            return _private.orgUnitChange(event,value);
        }
	    ,
        //控件事件描述:
        costedDeptChange:function(event,value){
            return _private.costedDeptChange(event,value);
        }
	    ,
        //控件事件描述:
        companyChange:function(event,value){
            return _private.companyChange(event,value);
        }
	    ,
        //控件事件描述:
        payerTypeChange:function(event,ui){
            return _private.payerTypeChange(event,ui);
        }
	    ,
        //控件事件描述:表头-收款人F7值改变事件
        payerNameF7Change:function(event,value){
            return _private.payerNameF7Change(event,value);
        }
	    ,
        //控件事件描述:
        payerBankChange:function(event,ui){
            return _private.payerBankChange(event,ui);
        }
	    ,
        //控件事件描述:
        payerBankF7Change:function(event,value){
            return _private.payerBankF7Change(event,value);
        }
	    ,
        //控件事件描述:表头-收款人账号值改变事件
        payerAccountF7Change:function(event,value){
            return _private.payerAccountF7Change(event,value);
        }
	    ,
        //控件事件描述:启用增值税报销
        enableTaxChecked:function(event,ui){
            return _private.enableTaxChecked(event,ui);
        }
	    ,
        //控件事件描述:分录表格-单元格保存事件
        editGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            return _private.editGridCellSave(rowid,cellname,value,iRow,Col,oldValue);
        }
	    ,
        //控件事件描述:分录表格-插入行之后事件
        editGridRowAdd:function(rowid,rowdata,rowelem){
            return _private.editGridRowAdd(rowid,rowdata,rowelem);
        }
	    ,
        //控件事件描述:分录载入完成事件
        editGridLoadAfter:function(data){
            return _private.editGridLoadAfter(data);
        }
	    ,
        //控件事件描述:单元格编辑事件
        editGridCellBefore:function(rowid,cellname,value,iRow,iCol,rowdata){
            return _private.editGridCellBefore(rowid,cellname,value,iRow,iCol,rowdata);
        }
	    ,
        //控件事件描述:
        editGridSelectBefore:function(rowid,e){
            return _private.editGridSelectBefore(rowid,e);
        }
	    ,
        //控件事件描述:
        columnCostCenterChange:function(event,value){
            return _private.columnCostCenterChange(event,value);
        }
	    ,
        //控件事件描述:
        columnCompanyChange:function(event,value){
            return _private.columnCompanyChange(event,value);
        }
	    ,
        //控件事件描述:
        columnCurrencyTypeChange:function(event,value){
            return _private.columnCurrencyTypeChange(event,value);
        }
	    ,
        //控件事件描述:
        columnExchangeRateChange:function(event,value){
            return _private.columnExchangeRateChange(event,value);
        }
	    ,
        //控件事件描述:
        ticketEntryloadComplete:function(data){
            return _private.ticketEntryloadComplete(data);
        }
	    ,
        //控件事件描述:冲借款分录-单元格值保存事件
        loanGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            return _private.loanGridCellSave(rowid,cellname,value,iRow,Col,oldValue);
        }
	    ,
        //控件事件描述:冲借款分录装载完成
        loanEntryLoadAfter:function(data){
            return _private.loanEntryLoadAfter(data);
        }
	    ,
        //控件事件描述:冲申请分录-单元格值保存事件
        reqGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            return _private.reqGridCellSave(rowid,cellname,value,iRow,Col,oldValue);
        }
	    ,
        //控件事件描述:冲申请分录装载完成
        reqEntryLoadAfter:function(data){
            return _private.reqEntryLoadAfter(data);
        }
	    ,
        //控件事件描述:
        epayerNameChange:function(event,ui){
            return _private.epayerNameChange(event,ui);
        }
	    ,
        //控件事件描述:
        ePayerBankChange:function(event,ui){
            return _private.ePayerBankChange(event,ui);
        }
	    ,
        //控件事件描述:多收款人分录-单元格值改变事件
        collectionGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            return _private.collectionGridCellSave(rowid,cellname,value,iRow,Col,oldValue);
        }
	    ,
        //控件事件描述:
        collectionGridLoadAfter:function(){
            return _private.collectionGridLoadAfter();
        }
	    ,
        //控件事件描述:
        collectionEntryF7Change:function(event,value){
            return _private.collectionEntryF7Change(event,value);
        }
	    ,
        //控件事件描述:
        entryPayerBankF7Change:function(event,value){
            return _private.entryPayerBankF7Change(event,value);
        }
	    ,
        //控件事件描述:
        entryPayerAccountChange:function(event,value){
            return _private.entryPayerAccountChange(event,value);
        }
	    ,
        //页面事件描述:页面渲染前事件
        pageOnRenderBefore:function(e){
            return _private.pageOnRenderBefore(e);
        }
	    ,
        //页面事件描述:实例载入前事件
        fieldOnLoadBefore:function(e){
            return _private.fieldOnLoadBefore(e);
        }
	    ,
        //页面事件描述:实例载入后事件
        fieldOnLoadAfter:function(e){
            return _private.fieldOnLoadAfter(e);
        }
	    ,
        //页面事件描述:页面载入完成后事件
        pageOnLoadAfter:function(e){
            return _private.pageOnLoadAfter(e);
        }
	    ,
        //页面事件描述:单据保存事件
        save:function(e){
            return _private.save(e);
        }
	    ,
        //页面事件描述:单据提交事件
        submit:function(e){
            return _private.submit(e);
        }
	    ,
        //页面事件描述:单据废弃事件
        abandon:function(e){
            return _private.abandon(e);
        }
	    ,
        //页面事件描述:单据删除
        bizCollBillDel:function(e){
            return _private.bizCollBillDel(e);
        }
	    ,
        //页面事件描述:查看借还款
        viewLoanPay:function(e){
            return _private.viewLoanPay(e);
        }
	    ,
        //页面事件描述:查看预算
        viewBudget:function(e){
            return _private.viewBudget(e);
        }
	    ,
        //页面事件描述:查看影像
        viewImage:function(e){
            return _private.viewImage(e);
        }
	    ,
        //页面事件描述:打印封面
        reportCover:function(e){
            return _private.reportCover(e);
        }
	    ,
        //页面事件描述:单据打印
        billReport:function(e){
            return _private.billReport(e);
        }
	    ,
        //页面事件描述:提交前事件
        submitBefore:function(e){
            return _private.submitBefore(e);
        }
	    ,
        //页面事件描述:选择收款人事件
        selectPayer:function(e){
            return _private.selectPayer(e);
        }
	    ,
        //页面事件描述:选择收款银行事件
        selectPayerBank:function(e){
            return _private.selectPayerBank(e);
        }
	    ,
        //页面事件描述:选择收款人账号事件
        selectPayerAccount:function(e){
            return _private.selectPayerAccount(e);
        }
	    ,
        //页面事件描述:增加分录行
        appendEntryRow:function(event,value){
            return _private.appendEntryRow(event,value);
        }
	    ,
        //页面事件描述:删除分录
        deleteEntryRow:function(event,value){
            return _private.deleteEntryRow(event,value);
        }
	    ,
        //页面事件描述:
        copyEntryRow:function(e){
            return _private.copyEntryRow(e);
        }
	    ,
        //页面事件描述:
        selectCloudInvoice:function(e){
            return _private.selectCloudInvoice(e);
        }
	    ,
        //页面事件描述:
        showCloudInvoice:function(e){
            return _private.showCloudInvoice(e);
        }
	    ,
        //页面函数描述:冲销借款分录新增行
        loanGridAddRow:function(e){
            return _private.loanGridAddRow(e);
        }
	    ,
        //页面函数描述:冲销借款分录删除行
        loanGridDelRow:function(e){
            return _private.loanGridDelRow(e);
        }
	    ,
        //页面函数描述:冲销申请新增行
        reqGridAddRow:function(e){
            return _private.reqGridAddRow(e);
        }
	    ,
        //页面函数描述:冲销申请删除行
        reqGridDelRow:function(e){
            return _private.reqGridDelRow(e);
        }
	    ,
        //页面函数描述:费用明细新增行
        editGridAddRow:function(e){
            return _private.editGridAddRow(e);
        }
	    ,
        //页面函数描述:费用明细删除行
        editGridDelRow:function(e){
            return _private.editGridDelRow(e);
        }
	    ,
        //页面函数描述:多收款人分录新增行
        collectionGridAddRow:function(e){
            return _private.collectionGridAddRow(e);
        }
	    ,
        //页面函数描述:多收款人分录删除行
        collectionGridDelRow:function(e){
            return _private.collectionGridDelRow(e);
        }
	    ,
        //页面函数描述:付现金额合法性校验
        validateEncashedAmount:function(value, dom){
            return _private.validateEncashedAmount(value, dom);
        }
	    ,
        //页面函数描述:收款金额校验
        validateCollectionAmount:function(value,dom){
            return _private.validateCollectionAmount(value,dom);
        }
	    ,
        //页面函数描述:分录-业务类别校验
        validateEntryOpertype:function(value,dom){
            return _private.validateEntryOpertype(value,dom);
        }
	    ,
        //页面函数描述:分录-业务类别校验
        validateEntryExpType:function(value,dom){
            return _private.validateEntryExpType(value,dom);
        }
	    ,
        //页面函数描述:电子发票下划线格式化
        ticketlink:function(rowId, gridId){
            return _private.ticketlink(rowId, gridId);
        }
	    ,
        //页面函数描述:费用承担部门、公司格式化函数
        companyCostFormatter:function(cellvalue, options, rowObject, act, iRow){
            return _private.companyCostFormatter(cellvalue, options, rowObject, act, iRow);
        }
	    ,
        //页面函数描述:币别、汇率格式化函数
        currencyrateFormatter:function(cellvalue, options, rowObject, act, iRow){
            return _private.currencyrateFormatter(cellvalue, options, rowObject, act, iRow);
        }
	    ,
        //页面函数描述:分录复制新增
        editGridCopyRow:function(e){
            return _private.editGridCopyRow(e);
        }
	    ,
        //页面函数描述:合计金额合法性校验
        validateAmount:function(value,dom){
            return _private.validateAmount(value,dom);
        }
	    ,
        //页面函数描述:承担公司部门必录效验
        validateCompanyCost:function(vakue,dom){
            return _private.validateCompanyCost(vakue,dom);
        }
	    ,
        //页面函数描述:发票分录税率（%）的格式化函数
        ticketTaxRateFormatter:function(cellvalue, options, rowObject){
            return _private.ticketTaxRateFormatter(cellvalue, options, rowObject);
        }
    });
        _self.subscribeEvent("pageOnRenderEvent",_public.pageOnRenderBefore,"before");
        _self.subscribeEvent("fieldOnLoadEvent",_public.fieldOnLoadBefore,"before");
        _self.subscribeEvent("fieldOnLoadEvent",_public.fieldOnLoadAfter,"after");
        _self.subscribeEvent("pageOnLoadEvent",_public.pageOnLoadAfter,"after");
        _self.subscribeEvent("saveActionEvent",_public.save,"overwrite");
        _self.subscribeEvent("submitActionEvent",_public.submit,"overwrite");
        _self.subscribeEvent("abandonActionEvent",_public.abandon,"overwrite");
        _self.subscribeEvent("deleteActionEvent",_public.bizCollBillDel,"overwrite");
        _self.subscribeEvent("viewLoanPayActionEvent",_public.viewLoanPay,"overwrite");
        _self.subscribeEvent("viewBudgetActionEvent",_public.viewBudget,"overwrite");
        _self.subscribeEvent("viewImageActionEvent",_public.viewImage,"overwrite");
        _self.subscribeEvent("reportCoverActionEvent",_public.reportCover,"overwrite");
        _self.subscribeEvent("reportActionEvent",_public.billReport,"overwrite");
        _self.subscribeEvent("submitActionEvent",_public.submitBefore,"before");
        _self.subscribeEvent("selectPayerActionEvent",_public.selectPayer,"overwrite");
        _self.subscribeEvent("selectPayerBankActionEvent",_public.selectPayerBank,"overwrite");
        _self.subscribeEvent("selectPayerAccountActionEvent",_public.selectPayerAccount,"overwrite");
        _self.subscribeEvent("appendEntryRowActionEvent",_public.appendEntryRow,"overwrite");
        _self.subscribeEvent("deleteEntryRowActionEvent",_public.deleteEntryRow,"overwrite");
        _self.subscribeEvent("copyEntryRowActionEvent",_public.copyEntryRow,"overwrite");
        _self.subscribeEvent("selectCloudInvoiceActionEvent",_public.selectCloudInvoice,"overwrite");
        _self.subscribeEvent("showCloudInvoiceActionEvent",_public.showCloudInvoice,"overwrite");
        //@endPublic
        //@beginTemplateFunctions

        //@endTemplateFunctions

        /**************************开发区域开始**********************************************/
        //@beginPrivate
        waf.extend(_private,{
                pageOnRenderBefore:function(e){
            // 控件渲染回调接口
            _self.setInitComponentCallBack(_public.setInitComponentOpt);
            //注册共享中心流程提交接口，用于共享节点配编辑页面提交时，做业务处理
            bizCollBillBase.addListenerForEidtPageSSCPass();
        }
	    ,
        fieldOnLoadBefore:function(e){
            bizCollBillBase.basePageOnRenderAfter();
            // 初始化多币别核销分录相关属性显示/隐藏
            bizCollBillBase.initCurrencyCheckAccountCss();
            // 实例载入前初始化页面样式
            _private.initPageCssBeforeField();
            // 初始化预算超额说明
//			_private.initBudgetDes();
            _public.initEntryData();
            _private.showProject();
            // 含税勾选框相关初始化
            _private.initIsWithTaxDefault();
            // 初始化收款人信息
            //_private.initPayerInfo();
            //初始化收款信息同步是否显示
            _private.showPayerInfoCheck();
        }
	    ,
        fieldOnLoadAfter:function(e){
            // 实例加载事件之后
//        	_public.refreshBaseInfoText();
//        	_public.refreshPayInfoText();
            // 表头相关F7初始化
            _private.initF7();
            // 实例载入后页面数据初始化.
            _private.initPageDataAfterField();
            _private.initPageCssAfterField();
            //审批界面配置编辑页控件显示处理
            bizCollBillBase.initPageCssWithAudit();
            _public.showTicket();
            _private.initEvent();
            if(_public.isLoanAccount){
                _private.fillLoanAccount();
            }
            _private.initInvoice();
        }
	    ,
        initPageCssBeforeField: function() {
            // 实例载入前,页面样式初始化
            var model = _self.getCurrentModel();
            //显示增值税  复选框 隐藏 方案隐藏无效
            waf("#enableTaxCheckbox").wafCheckbox("option", "hidden", true);
            // 超标说明有值时,显示超标说明
            var overAmountDesc = model.overAmountDesc;
            waf("#overAmountDescSection").wafTextarea("option", "hidden", !overAmountDesc);
            // 编辑时,如果有冲销申请,则显示冲销申请分录
            var showReqEntry = model.reqCheckEntries && model.reqCheckEntries.length > 0;
            //waf("#section_req").toggle(showReqEntry);
            //复制时，showReqEntry为null，导致toggle总是会执行反转
            if (!showReqEntry) {
                waf("#section_req").wafSection("option", "hidden", true);
            }
            // 配置了显示预算余额,显示预算信息文本
            waf("#bgTextColumn").toggle(bizCollBillBase.isShowBudgetBalance());
//			waf("#accountLimitTextColumn").toggle(bizCollBillBase.ifAccountStandard());
            //多收款人
            if(_public.useMorePayer){
                waf("#section_payerInfo").wafSection("option", "hidden", true);
                waf("#section_collectionAccount").wafSection("option", "hidden", false);
            } else {
                waf("#section_payerInfo").wafSection("option", "hidden", false);
                waf("#section_collectionAccount").wafSection("option", "hidden", true);
            }
            //编辑的时候是否显示超预算说明
            if(model.budgetDescription != null && model.budgetDescription != ''){
                //改变超预算说明
                _private.changeBudgetDescription(true);
            }
        }
	    ,
        changeBudgetDescription: function(flag) {
            if(!bizCollBillBase.isWarnOverBudget()) return;
            var budgetDescription = waf("#budgetDescription");
            //重置预算说明的样式
            if(flag == false || flag == "false"){
                waf("#section_budgetDescription").wafSection("option", "hidden", true);
                budgetDescription.wafValidator("option","rules","required:false");
                _public.budgetmaybechange = true;
            }else{
                waf("#section_budgetDescription").wafSection("option", "hidden", false);
                budgetDescription.wafValidator("option","errMsg","required:'"+_self._localeStr.BEYOND_BUDGET_STATE_NOT_NULL+"'");
                budgetDescription.wafValidator("option","rules","required:true");
            }
        }
	    ,
        showProject: function() {
            //显示项目
            var editGridDom = waf("#entries"),
                    reqGridDom = waf("#reqCheckEntries"),
                    loanGridDom = waf("#loanCheckEntries"),
                    showProjects = bizCollBillBase.isShowProject();
            editGridDom.wafGrid(showProjects ? "showColumn" : "hideColumn", ["project"]);
            reqGridDom.wafGrid(showProjects ? "showColumn" : "hideColumn", ["sourceBillProjectName"]);
            loanGridDom.wafGrid(showProjects ? "showColumn" : "hideColumn", ["sourceBillProjectName"]);
        }
	    ,
        initF7: function() {
            // F7控件初始化
            // 申请人F7过滤初始化
            var initdata = bizCollBillBase.getModelInitData(),
                    orgUnit = waf("#orgUnit").wafPromptBox("getValue"),
            adminOrgUnitIds = [],
            model = _self.getCurrentModel(),
                    companyId = model.applierCompany && model.applierCompany.id,
                    applierFilter = " id = 'nodata' ",
                    applierDom = waf("#applier");
            if (orgUnit && orgUnit.id) {
                adminOrgUnitIds = [orgUnit.id];
            } else if (initdata && initdata.adminOrgUnitIds) {
                adminOrgUnitIds = initdata.adminOrgUnitIds;
            }
            //applierDom.wafPromptSpecial_Person("option", "filteritem", applierFilter);
            //applierDom.wafPromptSpecial_Person("option", "beforeTableInit", _private.applierF7Init);
            //applierDom.wafPromptSpecial_Person("option", "filteritem", _private.getApplierFilter);
            // 申请人部门F7
            var orgUnitDom = waf("#orgUnit");
            var adminOrgUnitIds = "";
            if (initdata && initdata.adminOrgUnitIds) {
                adminOrgUnitIds = initdata.adminOrgUnitIds;
            }
            var orgUnitFilter = waf.parseSql.getFilter("unit.id","in",adminOrgUnitIds);
            orgUnitDom.wafPromptSpecial_Org("option", "filteritem", orgUnitFilter);
            // 申请人职位F7
            var positionDom = waf("#position");
            positionDom.wafPromptStandard("option", "filteritem", _public.getPositionFilter);
//			var model = _self.getCurrentModel();
//			if (model.applier) {
//				positionDom.wafPromptStandard("option", "filteritem", initdata.positionFilter);
//			} else {
//				positionDom.wafPromptStandard("option", "filteritem", "");
//			}
            positionDom.wafPromptStandard("option", "beforeTableInit", function(event, option) {
                var colModel = option.colModel;
                for (var i = 0, il = colModel.length; i < il; i++) {
                    var col = colModel[i];
                    if ("effectDate" == col.entityProName || "valiDate" == col.entityProName) {
                        col.formatter = "date";
                        col.formatoptions = {srcformat: "Y-m-d H:i:s", newformat: "Y-m-d"};
                    }
                }
            });
            // 多收款人F7初始化
            var collectionEntryF7 = waf("#collectionEntryF7");
            collectionEntryF7.wafPromptStandard("option", "defaultColumn", "payee");
            collectionEntryF7.wafPromptStandard("option", "defaultSearchItem", "payee+bankAccount+BebankStr");

            collectionEntryF7.wafPromptStandard("option", "filteritem", _public.getCollectionEntryFilter);
        }
	    ,
        applierF7Init: function(event, options) {
            // 申请人F7过滤条件拼接.
            options.beforeRequest = _private.beforeApplierRequest;
        },
        beforeApplierRequest: function(grid) {
            var postData = waf(grid).wafGrid("option", "postData"),
                    initData = bizCollBillBase.getModelInitData(),
                    model= _self.getCurrentModel(),
            adminOrgUnitIds = [],
            companyId = model.applierCompany && model.applierCompany.id;

            //部门不为空时
            var orgUnit = waf("#orgUnit").wafPromptBox("getValue");
            if(orgUnit){
                adminOrgUnitIds = [orgUnit.id];
            }else if(initData && initData.adminOrgUnitIds){
                adminOrgUnitIds = initData.adminOrgUnitIds;
            }

            var tmpFilter = postData.filterItems;
            var isShowCompanyPerson = bizCollBillBase.isShowAllCompanyStaff();
            if(isShowCompanyPerson){
                postData.filterItems = "(" + tmpFilter + ") or id in (select FReimbursePersonID from T_BC_ProxyReimburse " +
                        " where FProxyPersonID = '" + initData.personId + "' and FCompanyID = '"+ companyId +"' and fstate = 1) " +
                        " or adminOrgUnit.id in (select FCostCenterId from T_BC_ProxyReimburse where FCompanyID = '"+ companyId +"'";
                if (initData.adminOrgs.length > 0) {
                    postData.filterItems += " and FCostCenterId in ('" + Array.prototype.join.call(initData.adminOrgs, "\',\'") + "')"
                }
                postData.filterItems += ")";
            }else{
                postData.filterItems = " adminOrgUnit.id in ('" + Array.prototype.join.call(adminOrgUnitIds, "\',\'") + "') and " +
                        " deletedStatus != 2 ";
            }
//			var tmpFilter = postData.filterItems,
//			postData.filterItems = "(" + tmpFilter + ") or id in (select FReimbursePersonID from T_BC_ProxyReimburse " +
//				" where FProxyPersonID = '" + initData.personId + "' and FCompanyID = '"+ companyId +"' and fstate = 1) " +
//						"or adminOrgUnit.id in (select FCostCenterId from T_BC_ProxyReimburse where FCostCenterId " +
//						"in ('" + (Array.prototype.join.call(initData.adminOrgs, "\',\'") || "nodata")+ "') and FCompanyID = '"+ companyId +"')";
        },
        initPageDataAfterField: function() {
            // 实例载入完成后,页面数据初始化
            // botp适配类配置
            if (waf("#botp")) waf("#botp").botp("option", "billUserDefinedAdapter", _public.botpAdapter);
            // 初始化可用冲借款分录
            _private.initLoanGrid();
        }
	    ,
        initLoanGrid: function() {
            // 冲销借款分录初始化
            var initData = bizCollBillBase.getModelInitData(),
            selectedIds = [],
            model = _self.getCurrentModel(),
                    applierId = bizCollBillBase.getApplierId();
            if (_self.operateState.toUpperCase() != "ADDNEW" || String(initData.isCopy) == "true"
                    || !applierId || (_self.operateState.toUpperCase() == "ADDNEW" && !_public.ifInitLoanGrid)) {
                // 非新增状态|复制的单据,不加载可用冲借款
                if (!model.loanCheckEntries || model.loanCheckEntries.length == 0) {
                    waf("#section_loan").wafSection("close");
                }
                return;
            }
            if(model.loanCheckEntries) {
                for (var i = 0, il = model.loanCheckEntries.length; i < il; i++) {
                    // 关联生成且规则为不保存时,要过滤掉已有的 冲借款,并加载其余可用冲借款
                    selectedIds.push(model.loanCheckEntries[i].sourceBillEntryID);
                }
            }
            waf.doPost({
                    url: waf.getContextPath() + "/cp/bc/dynamic/bizAccountCheckSourcePage.do?method=initLoanGrid",
                    data: {applier: applierId, billType: "BizAccountOutBill",
                    selectedIds: waf.toJSONString(selectedIds)},
            showBlock: false,
                    success: function(data) {
                if ((data == null || data.rows == null || data.rows.length == 0)
                        && selectedIds.length == 0 ) {
                    waf("#section_loan").wafSection("close");
                    return;
                }
                var rows = data.rows;
                _private.doAppendLoanRow(rows);
            }
			});
        }
	    ,
        doAppendLoanRow: function(datas) {
            // 新增冲销借款
            var loanGridDom = waf("#loanCheckEntries"),
            addRows = [];
            var selectedRowId = loanGridDom.wafGrid("getSelectedRow");
            for (var i = 0, il = datas.length; i < il; i++) {
                var tmpData = datas[i];
                var	data = {
                        sourceBillNumber: tmpData.number,
                        sourceBillDate: tmpData.bizReqDate,
                        sourceBillCause: tmpData.cause,
                        sourceBillAmountApproved: tmpData.amountApproved,
                        sourceBillOperationType: tmpData.operationType,
                        sourceBillExpenseType: tmpData.expenseType,
                        sourceBillAmountBalance: tmpData.amountBalance,
                        sourceBillID: tmpData.sourceBillId,
                        sourceBillEntryID: tmpData.entryid,
                        sourceBillExpenseTypeId: tmpData.sourceBillExpenseTypeId,
                        // 非model中的字段,这里只能全部小写
                        sourcebillexpensetypedisplay: tmpData.expenseTypeDisplayName,
                        sourceBillCostCenterId: tmpData.sourceBillCostCenterId,
                        sourceBillCostCenterName: tmpData.sourceBillCostCenterName,
                        sourceBillProjectId: tmpData.sourceBillProjectId,
                        sourceBillProjectName: tmpData.sourceBillProjectName,
                        checkAmount: tmpData.amountBalance,
                        checkAmountOri: tmpData.amountBalanceOri,
                        exchangeRate: tmpData.exchangeRate,
                        convertMode: tmpData.convertMode,
                        currencyType: {id: tmpData.currencyTypeId, name: tmpData.currencyTypeName},
                exchangeRatePrecision: tmpData.exchangeRatePrecision,
                        hasSourceBill: tmpData.hasSourceBill,
                        sourceBillAmountBalanceOri: tmpData.amountBalanceOri
				};
                //loanGridDom.wafGrid("addRow", {data: data, position: "last"});
                if(_public.rowAddType =="add"){
                    loanGridDom.wafGrid("addRow", {data: data, position: "last"});
                }else{
                    loanGridDom.wafGrid("addRow", {data: data, position: "before",src: selectedRowId});
                }
            }
            // 刷新核销金额
            _public.refreshCheckAmountForAll();
            // 重新计算合计金额
            _public.refreshTotalAmount("loanCheckEntries", "loanAmount", "checkAmount");
            // 隐藏分录 | 冲销申请 | 收款人分录的浮动工具条
            waf("#entries, #reqCheckEntries, #editGrid_collection").wafGrid("hideFloatBar");
            // 刷新多币别合计
            bizCollBillBase.refreshCurrencyTotal("loanCheckEntries", "checkAmountOri",
                    "checkAmount", "loanCheckCurrencyTotalText", _self._localeStr.WRITE_OFFS, _public.multiCurrencyId);
        }
	    ,
        initPageCssAfterField: function() {
            waf("#toolBar_viewImage").wafLinkButton("option", "hidden", false);
            waf("#toolBar_viewProcessDiagram").wafLinkButton("option", "hidden", false);
            waf("#toolBar_viewOpinion").wafLinkButton("option", "hidden", false);
            waf("#toolBar_viewLoanPay").wafLinkButton("option", "hidden", false);
            waf("#toolBar_viewBudget").wafLinkButton("option", "hidden", false);
            waf("#toolBar_report").wafLinkButton("option", "hidden", false);
            waf("#toolBar_report1").wafMenuButton("option", "hidden", false);
            waf("#toolBar_reportCover1").wafMenuItem("option", "hidden", false);
            waf("#menu").wafMenuButton("option", "hidden", false);

            waf("#toolBar_copy").wafLinkButton("option", "hidden", false);
            waf("#toolBar_save").wafLinkButton("option", "hidden", false);


            waf("#toolBar_submit").wafLinkButton("option", "hidden", false);

            waf("#toolBar_delete").wafLinkButton("option", "hidden", false);
            waf("#menuItem_viewLoanPay").wafMenuItem("option", "hidden", false);
            waf("#menuItem_viewBudget").wafMenuItem("option", "hidden", false);
            waf("#menuItem_abandon").wafMenuItem("option", "hidden", false);
            waf("#menuItem_traceUp").wafMenuItem("option", "hidden", false);
            // 实例载入完成后,页面样式初始化
            // 事由加上必填样式
            waf("#section_cause").wafSection("option", "title", _self._localeStr.CAUSE+"<span style='margin-left:5px;color:red;font-size:14px;font-family: Arial;'>*</span>");
            waf("#section_budgetDescription").wafSection("option", "title", _self._localeStr.OVER_THAT+"<span style='margin-left:5px;color:red;font-size:14px;font-family: Arial;'>*</span>");
            // 附件样式
            var webAttachmentDom = waf("#webAttachment");
            var State = _self.operateState;
            if (webAttachmentDom.length > 0) {
                if (State == "ADDNEW" || State == "EDIT") {
                    webAttachmentDom.webAttachment("addFileDisable", false);
                    webAttachmentDom.webAttachment("deleteFileDisable", false);
                } else {
                    webAttachmentDom.webAttachment("addFileDisable", true);
                    webAttachmentDom.webAttachment("deleteFileDisable", true);
                }
                var datas = webAttachmentDom.webAttachment("getFileDatas");
            }
            // 工具栏初始化
            var billState = _self.getCurrentModel().state.value;
            switch(billState) {
                case 10:	//制单
                    waf("#toolBar_delete").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewImage").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewProcessDiagram").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewOpinion").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_report").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_report1").wafMenuButton("option", "hidden", true);
                    waf("#menu").wafMenuButton("option", "hidden", true);
                    break;
                case 20:	// 暂存
                    waf("#toolBar_viewImage").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewProcessDiagram").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewOpinion").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewLoanPay").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewBudget").wafLinkButton("option", "hidden", true);
                    waf("#menuItem_abandon").wafMenuItem("option", "hidden", true);
                    waf("#menu").wafMenuButton("option", "hidden", false);
                    break;
                case 25:	// 已提交
                case 40:	// 审核未通过
                    if (billState == 25) waf("#toolBar_save").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_delete").wafLinkButton("option", "hidden", true);
                    waf("#menuItem_viewLoanPay").wafLinkButton("option", "hidden", true);
                    waf("#toolBar_viewBudget").wafLinkButton("option", "hidden", true);
                    break;
                default:
                    break;
            }
            // 是否继承影响系统
            // TODO 去掉影像扫描相关组件显示
            var isPaperParkIntegration = bizCollBillBase.isPaperParkIntegration();
            if(billState != 10 && billState != 20){
                waf("#toolBar_viewImage").wafLinkButton("option", "hidden", !isPaperParkIntegration);
            }
            if(billState != 10){
                waf("#toolBar_report1").wafMenuButton("option", "hidden", !isPaperParkIntegration);
                waf("#toolBar_report").wafLinkButton("option", "hidden", isPaperParkIntegration);
            }
            // 是否参与预算控制
            var isNeedBudgetControl = bizCollBillBase.isNeedBudgetControl();
            if (!isNeedBudgetControl) {
                waf("#toolBar_viewBudget").wafLinkButton("option", "hidden", true);
                waf("#menuItem_viewBudget").wafMenuItem("option", "hidden", true);
            }
            // 新增时,隐藏赋值按钮
            if (State == "ADDNEW") {
                waf("#toolBar_copy").wafLinkButton("option", "hidden", true);
                waf("#toolBar_report1").wafMenuButton("option", "hidden", true);
            }
            var initData = bizCollBillBase.getModelInitData();

            // 去掉所有btn的enter2tab,防止tab键切换到焦点
            waf(".enter2tab.btn").removeClass("enter2tab");
            bizCollBillBase.removeFocusTab();
            //审批配置编辑页面时，需要隐藏一些按钮
            bizCollBillBase.initBtnCssFromWFOrSSC();
        }
	    ,
        initEvent: function() {
            // 事件初始化
            waf("#entries").wafGrid("option", "keyOptions", {lastCellAction: _private.editGridLastCellAdd});
            // 如果配置了预算显示,绑定分录表格的鼠标浮动显示预算的事件
            waf("#entries").wafGrid("option", "editModelOptions", {afterHover: _private.displayBgData});
            // 表头-申请日期值改变事件
//			waf("#bizReqDate").wafDatePicker("option", "onchange", _public.bizReqDateChange);
            // 点击页面其他区域,触发表格的保存
            $(document).bind("click.cpclick", function(e) {
//				if ($(e.target).parent().length > 0 && !$(e.target).closest(".ui-jqgrid-bdiv").length
//						&& !$(e.target).closest("#companyCostColumn").length
//						&& !$(e.target).closest("#currencyRateColumn").length) {
//					waf("#entries").wafGrid("editStop");
//					if (waf("#loanCheckEntries")) waf("#loanCheckEntries").wafGrid("editStop");
//					if (waf("#reqCheckEntries")) waf("#reqCheckEntries").wafGrid("editStop");
//					if (waf("#editGrid_collection")) waf("#editGrid_collection").wafGrid("editStop");
//					// 防止出发分录表格的editStop事件后,当前控件的焦点丢失的情况
//					var targetId = $(e.target).attr("id");
//					setTimeout(function() {$("#" + targetId).focus()}, 10);
//				}

                //分录汇率币别点击以外区域收起
                if (!$(e.target).closest("#currencyRateColumn").length) {
                    if(!(($(e.target).parent().length > 0 && $(e.target).parent()[0].editor != undefined && $(e.target).parent()[0].editor.opts.id.indexOf("currencyrate") > -1) ||
                            ($(e.target).length > 0 && $(e.target)[0].editor != undefined && $(e.target)[0].editor.opts.id.indexOf("currencyrate") > -1))) {
                        waf("#currencyRateColumn").hide();
                    }
                }
                //分录公司部门点击以外区域收起
                if (!$(e.target).closest("#companyCostColumn").length) {
                    if(!(($(e.target).parent().length > 0 && $(e.target).parent()[0].editor != undefined && $(e.target).parent()[0].editor.opts.id.indexOf("companyCost") > -1) ||
                            ($(e.target).length > 0 && $(e.target)[0].editor != undefined && $(e.target)[0].editor.opts.id.indexOf("companyCost") > -1))) {
                        waf("#companyCostColumn").hide();
                    }
                }
            });
            $("#bizAccountOutBill_Edit_layoutColumn15 .ui-jqgrid-titlebar").scroll(function() {
                waf("#entries").wafGrid("editStop");
            }
    		);
        }
	    ,
        editGridLastCellAdd: function(grid, rowid, colid) {
            // 分录表格按键输出新增一行
            waf("#entries").wafGrid("addRow", {src: rowid, data: _public.entryInitData,
                    position: "after"});
        }
	    ,
        displayBgData: function(grid, overlay, rowObj) {
            // 在分录表格上面显示预算信息
            var rowid = rowObj.attr("id");
            if (!rowid) return;
            //显示报销额度
            var limitStr = waf(grid).wafGrid("getCell", rowid, "accountStandard") || "";
            waf("#accountLimitText").show();
            waf("#accountLimitText").wafText("setValue", limitStr);
            if(!bizCollBillBase.isShowBudgetBalance()) return;
            var bgDataStr = waf(grid).wafGrid("getCell", rowid, "bgText") || "";
            waf("#bgtextArea").show();
            waf("#bgtextArea").wafTextarea("setValue",bgDataStr);
        }
	    ,
        fillLoanAccount: function(source, value) {
            //费用报销单上的备用金显示
            var loanAccount = waf("#loanAccount");
            var isRelaFi = false;
            var isShowLoanBalance = false;
            if (_self.getPageInitData()) {
                isRelaFi = _self.getPageInitData().CP002;
                isShowLoanBalance = _self.getPageInitData().CP011;
            }
            var billType = _self.getBillType();
            // 与总账继承&&启用借款余额提醒&&(费用报销单)才显示备用金
            if (String(isRelaFi) != "true" || String(isShowLoanBalance) != "true" || !loanAccount ||
                    $.inArray(billType, ["BizAccountBill","DailyPurchaseAccountBill","BizAccountOutBill"]) == -1) return;
            waf("#loanAccountColumn").show();
            var applier = waf("#applier").wafPromptBox("getValue"),
                    costedDept = waf("#costedDept").wafPromptBox("getValue"),
                    orgUnit = waf("#orgUnit").wafPromptBox("getValue"),
                    applierId = "",
                    costedDeptId = "",
                    orgUnitId = "",
                    applierId = "",
                    currencyTypeId = null,
                    applierCompany = waf("#applierCompany").wafPromptBox("getValue");
            switch (source) {
                case undefined:
                case null:
                    if (applier) applierId = applier.id;
                    if (costedDept) costedDeptId = costedDept.id;
                    if (_self.getCurrentModel() && _self.getCurrentModel().currencyType)
                        currencyTypeId = _self.getCurrentModel().currencyType.id;
                    if (orgUnit) orgUnitId = orgUnit.id;
                    break;
                case "orgUnit":
                    if (applier) applierId = applier.id;
                    if (costedDept) costedDeptId = costedDept.id;
                    if (_self.getCurrentModel() && _self.getCurrentModel().currencyType)
                        currencyTypeId = _self.getCurrentModel().currencyType.id;
                    if(value.current) orgUnitId = value.current.id;
                    break;
                case "applier":
                    if (value.current) applierId = value.current.id;
                    if(costedDept) costedDeptId = costedDept.id;
                    if(_self.getCurrentModel() && _self.getCurrentModel().currencyType)
                        currencyTypeId = _self.getCurrentModel().currencyType.id;
                    if(orgUnit) orgUnitId = orgUnit.id;
                    break;
                case "costedDept":
                    if(applier) applierId = applier.id;
                    if(value.current) costedDeptId = value.current.id;
                    if(_self.getCurrentModel() && _self.getCurrentModel().currencyType)
                        currencyTypeId = _self.getCurrentModel().currencyType.id;
                    if(orgUnit) orgUnitId = orgUnit.id;
                    break;
                default:
                    break;
            }
            waf.doPost({
                    action: "onchange",
                    data: {source: "valueChangeForAccount", applierId: applierId, costedDeptId: costedDeptId,
                    companyId: (applierCompany && applierCompany.id) || "",
                    currencyTypeId: currencyTypeId, orgUnitId: orgUnitId, billType: billType},
            success:function(result) {
                loanAccount.wafText("option", "hidden", !result);
                loanAccount.wafText("option", "value", result || "");
            }
			});
        }
    	,
        editGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            // editGrid单元格值保存事件
            var gridDom = waf("#entries");
            switch (cellname) {
                case "operationType":	// 业务类别值改变事件
                    // 费用类型值清空
                    if (!_public.needChangeEvent) return;
                    var expenseType = waf("#entries").wafGrid("getCell", rowid, "expenseType");
                    if (!value || (expenseType && expenseType.operationType.id != value.id)) {
                        gridDom.wafGrid("setCell", rowid, "expenseType", null);
                    }
                    // 费用类型自定义F7设置过滤
                    _private.entryOperationTypeChange(rowid, value);
                    // 业务类别改变触发预算查询
                    _private.showBgData("operationType", value, false, rowid, true);
                    //改变超预算说明
                    _private.changeBudgetDescription(false);
                    break;
                case "expenseType":	// 费用类型值改变事件
                    var operationType = value ? value.operationType : {};
                    var editGridDom = waf("#entries");
                    _public.needChangeEvent = false;
                    editGridDom.wafGrid("setCell", rowid, "operationType", operationType);
                    _public.needChangeEvent = true;
                    // 设置费用类型过滤
                    var editoptions = editGridDom.wafGrid("getCellConfig", "expenseType", rowid).editoptions;
                    editoptions.subTagJson.operationTypeId = value ? value.operationType.id : "nodata";
                    editGridDom.wafGrid("setCellConfig", "expenseType", "editoptions", editoptions, rowid);
                    // 触发预算查询
                    _private.showBgData("expenseType", value, false, rowid, true);
                    //改变超预算说明
                    _private.changeBudgetDescription(false);
                    break;
                case "company":	// 费用支付公司值改变事件
                    _private.entryCompanyChange(rowid, value);
                    if (_public.isNeedClearCostCenter) {
                        gridDom.wafGrid("setCell", rowid, "costCenter", null);
					/*// 清空当前行费用类型/业务累呗
					gridDom.wafGrid("setCell", rowid, "operationType", null);
					gridDom.wafGrid("setCell", rowid, "expenseType", null);*/
                    }
                    //gridDom.wafGrid("setCell", rowid, "operationType", null);
                    var operationTypeId = null;
                    var rowId = gridDom.wafGrid("getSelectedRow");
                    var operationType = gridDom.wafGrid("getCell", rowId, "operationType")
                    if(operationType != ""){
                        operationTypeId = operationType.id;
                    }
                    bizCollBillBase.isNeedClearOperationTypeByChangeCompany(rowid, value, operationTypeId);
                    // 触发预算查询
                    _private.showBgData("company", value, false, rowid, true);
                    //改变超预算说明
                    _private.changeBudgetDescription(false);
                    //置空项目，实现项目与公司隔离
                    gridDom.wafGrid("setCell", rowid, "project", null);
                    break;
                case "costCenter":	// 费用支付部门值改变事件
                    // 清空当前行费用类型/业务累呗
                    var operationTypeId = null;
                    var rowId = gridDom.wafGrid("getSelectedRow");
                    var operationType = gridDom.wafGrid("getCell", rowId, "operationType")
                    if(operationType != ""){
                        operationTypeId = operationType.id;
                    }
                    _private.entryCostCenterChange(rowid, value, operationTypeId);
                    // 触发预算查询
                    _private.showBgData("costCenter", value, false, rowid, true);
                    //改变超预算说明
                    _private.changeBudgetDescription(false);
                    break;
                case "amount":	// 分录-本位币值改变
                    _private.entryAmountChange(rowid, value, oldValue);
                    break;
                case "amountOri":	// 分录-原币值改变
                    _public.entryAmountOriChange(rowid, value);
                    // 刷新本位币不含税金额
                    _public.refreshEntryTaxAmount(rowid);
                    break;
                case "currencyType":	// 分录-币别值改变
                    _private.entryCurrencyTypeChange(rowid, value);
                    break;
                case "exchangeRate":	// 分录-汇率值改变事件
                    //更新币别缓存
                    _private.updateCurrencyMap();
                    _private.entryExchangeRateChange(rowid, value);
                    break;
                case "exchangeRatePrecision":	// 分录-汇率精度值改变事件
                    break;
                case "happenTime":	// 分录-费用发生时间
                    break;
                case "project":	// 分录-项目
                    // 触发预算查询
                    _private.showBgData("project", value, false, rowid, true);
                    // 改变超预算说明
                    _private.changeBudgetDescription(false);
                    break;
                case "amountWithoutTax":	// 分录-不包含税金额
                    _public.refreshEntryTaxAmount(rowid);
                    _public.entryTaxRateChange(rowid);
                    break;
                case "taxRate":	// 分录-税率
                    _public.entryTaxRateChange(rowid, value);
                    // 刷新本位币不含税金额
                    _public.refreshEntryTaxAmount(rowid);
                    break;
                case "tax":	// 分录-税额
                    _public.entryTaxChange(rowid, value);
                    // 刷新本位币不含税金额
                    _public.refreshEntryTaxAmount(rowid);
                    break;
                case "amountOriWithoutTax":	// 分录-不包含税金额
                    _public.refreshEntryTaxAmount(rowid);
                    _public.entryTaxRateChange(rowid);
                    break;
                case "currencyrate":
                    //更新币别缓存
                    _private.updateCurrencyMap();
                    _private.entryExchangeRateChange(rowid, value);
                    break;
                default:
                    break;
            }
        }
    	,

        entryOperationTypeChange: function(rowid, value) {
            //  分录-业务类别值改变
            var editGridDom = waf("#entries");
            var editoptions = editGridDom.wafGrid("getCellConfig", "expenseType", rowid).editoptions;
            editoptions.subTagJson.operationTypeId = value ? value.id : "nodata";
            editGridDom.wafGrid("setCellConfig", "expenseType", "editoptions", editoptions, rowid);
        }
    	,
        showBgData: function(prop, value, isHeadChange, rowid, ifStore) {
            // 显示预算
            // 参数没配置预算||数据加载的时候,不触发预算查询
            if (!bizCollBillBase.isShowBudgetBalance()) return;
            if(prop!=null && value==null){
                waf("#bgtextArea").hide();
                waf("#entries").wafGrid("setCell", rowid, "bgText", null);
                return;
            }
            if (ifStore) _self.fireEvent("fieldOnStoreEvent");
            var model = waf.cloneObject(_self.getCurrentModel());
            if (!isHeadChange) {
                var entry = waf("#entries").wafGrid("getRowRealData", rowid);
                model.entries = [entry];
            } else{
                rowid = waf("#entries").wafGrid("getDataIDs");
            }
            waf.invokeCPBCBizCtrl({
                    service: "com.kingdee.eas.cp.bc.app.CommonWebUtilFacade",
                    serviceMethod: "getBudgets",
                    parameters: [model],
            showBlock: false,
                    success: function(data) {
                var rowIds = isHeadChange ? waf("#entries").wafGrid("getDataIDs") : [rowid];
                for (var i =0, il = rowIds.length; i < il; i++) {
                    var rowId = rowIds[i];
                    var budget = data[i];
                    waf("#entries").wafGrid("setCell", rowId, "bgText", bizCollBillBase.formatBudgets(data[i]) || "");
                }
                if (rowid) {
                    // 如果是分录-单元格值改变出发预算重新查询,则直接更新表头显示的预算信息文本
                    waf("#bgtextArea").show();
                    waf("#bgtextArea").wafTextarea("setValue", bizCollBillBase.formatBudgets(data[0]));
                }
            }
			});
        }
    	,
        entryCostCenterChange: function(rowid, value, operationTypeId) {
            // 分录-费用支付部门值改变
            if (!value) return;
            var editGridDom = waf("#entries");
            var company = editGridDom.wafGrid("getCell", rowid, "company");
            var billType = _self.getBillType();
            //	if (company) return;
            waf.doPost({
                    action: "onchange",
                    data: {source: "costCenter", costCenterId: value.id, operationTypeId: operationTypeId, billType: billType},
            showBlock: false,
                    success: function(result) {
                _public.isNeedClearCostCenter = false;
                // 锁定单元格,赋值的时候刚好点击公司F7,导致公司值被清空
                //editGridDom.wafGrid("lockCell", rowid, "company");
                editGridDom.wafGrid("setCell", rowid, "company", result.company);
                //editGridDom.wafGrid("unLockCell", rowid, "company");
                _public.needChangeEvent = false;
                waf("#entryCompany").wafPromptBox("setValue", result.company);
                _public.needChangeEvent = true;
                // 这里得手动触发公司的值改变事件,进行部门的过滤设置
                //_private.entryCompanyChange(rowid, result.company);
                _public.isNeedClearCostCenter = true;
                if(result.isNeedClearOperationType){
						/*var editoptions = editGridDom.wafGrid("getCellConfig", "expenseType", rowid).editoptions;
						editoptions.subTagJson.operationTypeId = null;
						editGridDom.wafGrid("setCellConfig", "expenseType", "editoptions", editoptions, rowid);*/
                    editGridDom.wafGrid("setCell", rowid, "operationType", null);
                    editGridDom.wafGrid("setCell", rowid, "expenseType", null);
                }
            }
			});
        }
    	,
        entryCompanyChange: function(rowid, value) {
            // 分录-费用支付公司值改变
            var companyId = value ? value.id : null;
            var editGridDom = waf("#entries");

            // 费用类型设置companyId;
            var company = value || waf("#applierCompany").wafPromptBox("getValue"),
                    expenseTypeOptions = editGridDom.wafGrid("getCellConfig", "expenseType", rowid).editoptions;
            expenseTypeOptions.subTagJson.companyId = (company && company.id) || "";
            editGridDom.wafGrid("setCellConfig", "expenseType", "editoptions", expenseTypeOptions, rowid);
        }
    	,
        build_costCenter_filter: function() {
            // 获取费用支付部门F7过滤
            var filter = "";
            var initData = bizCollBillBase.getModelInitData();
            if (initData && initData.CP019 && String(initData.CP019) == "true") {

                var filterTmp = waf.parseSql.getFilter("id","in",initData.orgRangeSet||"nodata");

                filter = waf.parseSql.mergeFilter([filter,filterTmp],"and");
                //"AND id IN ('" + (Array.prototype.join.call(initData.orgRangeSet, "\',\'") || "'nodata'") + "')";
            }
            return filter;
        }
    	,
        entryAmountChange: function(rowid, value, oldValue) {
            // 触发分录表格的总金额求值
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        }
    	,
        entryCurrencyTypeChange: function(rowid, value) {
            //if (!waf("#cbMultiCurrency").wafCheckbox("isChecked")) return;
            var editGridDom = waf("#entries")
            var exchangeRatePrecision = 4,
                    exchangeRate = 0,
                    convertMode = 0;
            if (value){
                //获取汇率
                var result = bizCollBillBase.getExRateBycurrency(value);
                if (result) {
                    exchangeRatePrecision = result.precision;	// 汇率精度
                    exchangeRate = result.convertRate;	// 汇率
                    convertMode = result.convertMode;	// 汇率折算方式
                }
            }
            waf("#entryExchangeRate").wafNumberField("option","decimalPrecision",exchangeRatePrecision);
            waf("#entryExchangeRate").wafNumberField("setValue", exchangeRate, false);
            // 分录-汇率精度赋值
            editGridDom.wafGrid("setCell", rowid, "exchangeRatePrecision", exchangeRatePrecision);
            // 分录-汇率赋值
            editGridDom.wafGrid("setCell", rowid, "exchangeRate", exchangeRate);
            // 分录-折算方式赋值
            editGridDom.wafGrid("setCell", rowid, "convertMode", convertMode);
            //设置汇率精度。该操作框架会调用editStop，触发currencyrateFormatter(不是每次都触发，只有币别/汇率字段为编辑状态才会触发)，所以放在后面
            editGridDom.wafGrid("setCellEditorAllConfig", "exchangeRate", "decimalPrecision", exchangeRatePrecision, "numberField", rowid);

            var rowObject = editGridDom.wafGrid("getRowRealData", rowid);//获取分录控件值
            var currencyrateValue = bizCollBillBase.currencyrateFormatter(null,null,rowObject,null,null);
            //设置币别/汇率字段。这种方式可以确保该字段正确，解决连续多次修改币别，币别/汇率会显示错误问题
            editGridDom.wafGrid("setCell", rowid, "currencyrate", currencyrateValue);

            //更新币别缓存
            _private.updateCurrencyMap();
            _public.refreshEntryAmount(rowid);
            _public.refreshTotalAmount("entries", "amount", "amount", true);
            // 刷新本位币不含税金额
            _public.refreshEntryTaxAmount(rowid);
        }
    	,
        updateCurrencyMap: function() {
            var entryDom = waf("#entries");
            var dataIds = entryDom.wafGrid("getDataIDs");
            _public.currencyMap.clear();
            for(var i = 0; i < dataIds.length; i++){
                var currency = entryDom.wafGrid("getCell", dataIds[i], "currencyType");
                if(currency == null) continue;
                var object = {};
                object.exchangeRate = entryDom.wafGrid("getCell", dataIds[i], "exchangeRate"),
                        object.exchangeRatePrecision = entryDom.wafGrid("getCell", dataIds[i], "exchangeRatePrecision"),
                        object.convertMode = entryDom.wafGrid("getCell", dataIds[i], "convertMode");
                _public.currencyMap.put(currency.id,object);
            }
            var model = _self.getCurrentModel();
            if(model.currencyType != null){
                _public.currencyMap.put(model.currencyType.id,
                        {exchangeRate:1.0000,exchangeRatePrecision:4,convertMode:0});
            }
        }
    	,
        entryExchangeRateChange: function(rowid, value) {
            // 更新本位币金额
            _public.refreshEntryAmount(rowid);
            // 刷新合计金额
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        }
    	,
        loanGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            bizCollBillBase.loanGridCellSave(rowid,cellname,value,iRow,Col,oldValue);
            // 刷新付现金额
            _public.refreshEncashedAmount();
        }
    	,
        reqGridCellSave:function(rowid,cellname,value,iRow,Col,oldValue){
            bizCollBillBase.reqGridCellSave(rowid,cellname,value,iRow,Col,oldValue);
        }
    	,
        bizReqDateChange:function(changeValue,inst){
            var date = changeValue.current;
            var preDate = changeValue.previous;
            if(!date) {	//空值处理
                waf("#bizReqDate").wafDatePicker("setValue", preDate, false);
                return;
            }
            if (date) date = date.format("yyyy-MM-dd hh:mm:ss");
            if(preDate) preDate = preDate.format('yyyy-MM-dd hh:mm:ss');
            // 触发预算查询
            _private.showBgData("bizReqDate", date, true, true, true);
            //改变超预算说明
            _private.changeBudgetDescription(false);

            //申请日期发生改变后，检查汇率是否有变动，并获取有变动的币别汇率Map
            var exRateChangeCurrencyMap = bizCollBillBase.getExRateChangeCurrencyMap(preDate, date);
            if(exRateChangeCurrencyMap && exRateChangeCurrencyMap.size()>0){
                var buttons = [{
                    text: _self._localeStr.YES,
                            click: function() {
                        //根据最新的币别汇率Map刷新分录和收款分录的汇率和金额
                        _public.updateCurrencyExRateAndAmount(exRateChangeCurrencyMap);
                    }
                }, {
                    text: _self._localeStr.NO,
                            click: function() {}
                }];
                var confirmOption = {
                        summaryMsg: _self._localeStr.IS_UPDATE_EXRATE || bizCollBillBase.IS_UPDATE_EXRATE,
                        buttons: buttons
					};
                waf.msgBox.showConfirm(confirmOption);
            }
        }
    	,
        applierChange:function(event,value){
            if (!_public.needChangeEvent) return;
            if (!value.current) {
                var positionDom = waf("#position");
                positionDom.wafPromptBox("setValue", null);
//				positionDom.wafPromptStandard("option", "filteritem", "");
                return;
            }
            waf.doPost({
                    action: "onchange",
                    data: {source:"applier", applier:value.current.id},
            showBlock: false,
                    success: function(result) {
                // 报销人部门赋值
                var orgUnit = result.mainOrg,
                        orgUnitDom = waf("#orgUnit");
                waf("#orgUnit").wafPromptBox("setValue", result.mainOrg || null,false);
                //_private.fillLoanAccount("applier", value);
                // 报销人职位赋值
                var positionDom = waf("#position");
                positionDom.wafPromptBox("setValue", result.mainPosition);

                //表头费用承担公司、部门变更
                _public.isCostCenterChange = true;
                waf("#company").wafPromptBox("setValue", result.company);
                _public.isCostCenterChange = false;
                waf("#costedDept").wafPromptBox("setValue", result.costedDept,false);
            }
			});
            var initData = bizCollBillBase.getModelInitData();
            //cp014为否，cp052无论是什么，单头申请人改变，清空冲借款分录
            if(String(initData.CP014) == "false"){
                waf("#loanCheckEntries").wafGrid("clearGridData");
            }
        }
    	,
        orgUnitChange: function(event, value) {
            // 申请人部门值改变事件
            // 申请人职位F7值/过滤清空
            var positionDom = waf("#position");
            _private.fillLoanAccount("orgUnit", value);
//			positionDom.wafPromptBox("setValue", null);
////			positionDom.wafPromptStandard("option", "filteritem", "");
//			// 申请人F7值清空
//			var applierDom = waf("#applier");
//			_public.needChangeEvent = false;
//			applierDom.wafPromptBox("setValue", null);
//			_public.needChangeEvent = true;
            var applier = waf("#applier").wafPromptBox("getValue");
            // 联动职位/申请人
            waf.doPost({
                    action: "BizCollBillEdit",
                    data: {_method: "orgUnitChange", applierId: (applier && applier.id) || "",
                    orgUnitId: (value.current && value.current.id) || ""},
            async: false,
                    success: function(result) {
                // 当前申请人是否再所选部门中,如果不在,则清空申请人F7
                if (!result.userInOrgUnit) {
                    _public.needChangeEvent = false;
                    waf("#applier").wafPromptBox("setValue", null);
                    _public.needChangeEvent = true;
                }
                // 职位F7变更
                waf("#position").wafPromptBox("setValue", result.position);
                _public.isCostCenterChange = true;
                waf("#company").wafPromptBox("setValue", result.company);
                _public.isCostCenterChange = false;
                waf("#costedDept").wafPromptBox("setValue", result.costedDept,false);
            }
			});
            var initData = bizCollBillBase.getModelInitData();
            //cp014为是，部门改变，清空冲借款分录
            if(String(initData.CP014) == "true"){
                waf("#loanCheckEntries").wafGrid("clearGridData");
            }
        }
    	,
        initEntriesForOtherDept: function(isOtherDept, operColumn) {
            var editGridDom = waf("#entries");
            editGridDom.wafGrid("hideColumn", ["company", "costCenter"]);
            // 其他部门承担费用显示/隐藏
            editGridDom.wafGrid(isOtherDept ? "showColumn" : "hideColumn", operColumn);
        }
    	,
        initMultiInfo: function() {
            // 多币别/其他部门承担费用初始化
            var isMultiCurrency = false,
                    isOtherDept = false,
                    isOtherCompany = false,
                    isEnableTax = false,
                    isWithTax = true,
                    model = _self.getCurrentModel(),
                    initData = bizCollBillBase.getModelInitData(),
                    baseCurrencyId = model.currencyType ? model.currencyType.id : "nodata",
                    baseCompanyId = model.company ? model.company.id : "nodata",
                    baseCostCenterId = model.costedDept ? model.costedDept.id : "nodata",
                    currencyCheckDom = waf("#cbMultiCurrency"),
                    otherDeptCheckDom = waf("#cbOtherDept"),
                    otherCompanyCheckDom = waf("#cbOtherDeptAndCompany"),
                    enableTaxCheckDom = waf("#enableTaxCheckbox"),
                    isWithTaxCheckDom = waf("#isWithTax");
            if (_self.operateState == "ADDNEW" && initData && String(initData.isCopy) != "true"
                    && String(initData.fromCreateTo) != "true") {
                // 只有新增(复制新增/关联生成除外),初始化页面时,多币别勾选可受方案中的配置值控制
                isMultiCurrency = currencyCheckDom.wafCheckbox("isChecked");
                isOtherDept = otherDeptCheckDom.wafCheckbox("isChecked");
                isOtherCompany = otherCompanyCheckDom.wafCheckbox("isChecked");
                isEnableTax = enableTaxCheckDom.wafCheckbox("isChecked");
            }
            if (model.entries && model.entries.length > 0) {
                for (var i = 0; i < model.entries.length; i++) {
                    var entry = model.entries[i];
                    if (!isMultiCurrency && (entry.currencyType && entry.currencyType.id) != baseCurrencyId) {
//	        			waf("#cbMultiCurrency").wafCheckbox("setChecked",true);
                        isMultiCurrency = true;
                    }
                    if (!isOtherDept && (entry.costCenter && entry.costCenter.id) != baseCostCenterId) {
                        isOtherDept = true;
                    }
                    if (!isOtherCompany && (entry.company && entry.company.id) != baseCompanyId) {
                        isOtherCompany = true;
                    }
                    if (!isEnableTax && (entry.taxRate > 0)) {
                        isEnableTax = true;
                    }
                    if (isMultiCurrency && isOtherDept && isOtherCompany && isEnableTax) {
                        break;
                    }
                }
            }

//			waf("#cbMultiCurrency").wafCheckbox("isChecked"),
            // 先取消多币别勾选框的值改变事件,防止多余的触发操作.初始化完成后再绑定值改变事件.
            currencyCheckDom.wafCheckbox("option", "onchange", null);
            currencyCheckDom.wafCheckbox("setChecked", isMultiCurrency);

            currencyCheckDom.wafCheckbox("option", "onchange", _public.cbMultiCurrencyChange);
            waf("#entries").wafGrid("calcFooterData");
            bizCollBillBase.refreshCurrencyTotal("entries", "amountOri", "amount", "currencyTotalText", _self._localeStr.REIMBURSEMENT_AMOUNT, _public.multiCurrencyId);
            var totalAmount =  waf("#entries").wafGrid("getColValue", "amount", false, "sum");
            waf("#amount").wafNumberField("setValue", totalAmount);
            // 税率相关初始化
//			enableTaxCheckDom.wafCheckbox("option", "onchange", null);
//			enableTaxCheckDom.wafCheckbox("setChecked", isEnableTax);
//			_private.initEntryTaxChecked(isEnableTax);
//			enableTaxCheckDom.wafCheckbox("option", "onchange", _public.enableTaxChecked);
        }
    	,
        initBgData: function() {
            // 初始化分录预算信息
            if (!bizCollBillBase.isShowBudgetBalance()) return;
            // 显示预算信息
            waf("#bgTextColumn").show();
            var initData = bizCollBillBase.getModelInitData();
            // 复制新增的单据,需要预算初始化
            if("ADDNEW" == _self.operateState && !_private.initDataWhenAddNew()
                    && String(initData.isCopy) != "true") return;
            _private.showBgData(null, null, true, false);
        }
    	,
        initDataWhenAddNew: function() {
            //从额度那里报销过来的单据要初始化信息
            var entrys = _self.getCurrentModel().entries;
            if(entrys && entrys[0] && entrys[0].expenseType){
                return true;
            }
            return false;
        }
    	,
        setAccessoryCount: function() {
            // 设置附件数
            if (waf("#webAttachment") && waf("#accessoryCount")) {
                var attachmentSize = 0;
                if($("li.ui-fileupload-download-info")) {
                    attachmentSize = $("li.ui-fileupload-download-info").length;
                }
                if(attachmentSize>0){
                    waf("#accessoryCount").wafNumberField("setValue", attachmentSize);
                }
            }
        }
    	,
        submitFunc: function(editModel) {
            var result = true;
            var initData = bizCollBillBase.getModelInitData();
            var actionName = "submit";
            var isAsync = true;//是否异步,单据直接新增或编辑提交用异步
            if(bizCollBillBase.isFromWFSubmitWeb || bizCollBillBase.isFromSSCSubmitWeb){
                isAsync = false;//审批配编辑页面提交单据要用同步提交
            }
            // 提交操作Func
            waf.doPost({
                    action: actionName,
                    async: isAsync,
                    data: {model: editModel,operateState: _self.operateState,
//					needCheckNextPartipants: _self.getNeedCheckNextPartipants(),
                    nextPartipants: _self.getNextPartipants()},
            success: function(data) {
                _public.budgetmaybechange = false;
                if(data.isBudgetPass != null){
                    _private.checkBudgetSuccess(data);
                    //审批配编辑页面时,超预算后，result要置为false,不让流程提交成功
                    if(bizCollBillBase.isFromWFSubmitWeb || bizCollBillBase.isFromSSCSubmitWeb){
                        result = false;
                    }
                } else {
                    if(bizCollBillBase.isFromWFSubmitWeb){
                        //用于流程中心审批页面配编辑页面+edit时，获取业务提交后最新的model并更新到页面model和控件中，供工作流提交使用
                        bizCollBillBase.refreshCurrentModelAfterFromWFSubmit();
                    }
                    if(bizCollBillBase.isFromWFSubmitWeb || bizCollBillBase.isFromSSCSubmitWeb){
                        //审批配编辑页面时，不需要做后续代码处理，直接retrun;
                        return;
                    }

                    var urlParam = waf.getUrlParams(document.location.href);
                    var billType = _self.getBillType();
                    var isNeedPrint = false;
                    //出差申请单、借款单、出差借款单 、费用申请单不需要打印
                    if("EvectionReqBill" != billType && "DailyLoanBill"!= billType && "EvectionLoanBill"!= billType && "OtherExpenseBill"!= billType){
                        isNeedPrint = true;
                    }
                    if(urlParam["isFromWorkflow"] == "true") {
                        waf.msgBox.showInfo({
                                summaryMsg: _self._localeStr.SUBMITTED_SUCCESSFULLY,
                                buttonType: "ok",
                                buttonCallBack: [function(dialog) {
                            _self.setJudgeDataModify(false);
                            var buttons = [{text: _self._localeStr.YES, click: function(){
                                _self._pageInitByModelLoad(data)
                                _self._reportActionEventHandler();
                            }},
                            {text: _self._localeStr.NO, click: function() {
                                //点否 cp037为否直接返回管理控制台
                                if(String(initData.CP037) == "true"){
                                    setTimeout(function(){
                                        _self.fireEvent("exit");
                                    },_public.cp037TimeOut);
                                }else{
                                    _self._pageInitByModelLoad(data);
                                }
                            }}];
                            var confirmOption = {
                                    summaryMsg: _self._localeStr.WHETHER_TO_PRINT_IMMEDIATELY,
                                    buttons: buttons
									};
                            if (isNeedPrint && String(initData.CP036) == "true"){
                                waf.msgBox.showConfirm(confirmOption);
                                if(waf.isInFramePage()&&(top.portal||(top==parent && $(parent.document.body).hasClass("wafPage")))){
                                    waf(".waf-dialog-titlebar-close", parent.document).hide();
                                }
                                else{
                                    waf(".waf-dialog-titlebar-close").hide();
                                }
                            }else if (String(initData.CP037) == "true"){
                                setTimeout(function(){
                                    _self.fireEvent("exit");
                                },_public.cp037TimeOut);
                            } else{
                                _self.showSuccess(waf.wafEdit.info.submitsuccess);
                                _self._pageInitByModelLoad(data)
                            }
                        }]
							});
                    } else {
                        _self.setJudgeDataModify(false);
                        var buttons = [{text: _self._localeStr.YES, click: function(){
                            _self._pageInitByModelLoad(data)
                            _self._reportActionEventHandler();
                        }},
                        {text:_self._localeStr.NO, click: function() {
                            //点否 cp037为否直接返回管理控制台
                            if(String(initData.CP037) == "true"){
                                setTimeout(function(){
                                    _self.fireEvent("exit");
                                },_public.cp037TimeOut);
                            }else{
                                _self._pageInitByModelLoad(data);
                            }
                        }}];
                        var confirmOption = {
                                summaryMsg: _self._localeStr.WHETHER_TO_PRINT_IMMEDIATELY,
                                buttons: buttons
							};
                        if (isNeedPrint && String(initData.CP036) == "true"){
                            waf.msgBox.showConfirm(confirmOption);
                            if(waf.isInFramePage()&&(top.portal||(top==parent && $(parent.document.body).hasClass("wafPage")))){
                                waf(".waf-dialog-titlebar-close", parent.document).hide();
                            }
                            else{
                                waf(".waf-dialog-titlebar-close").hide();
                            }
                        }else if (String(initData.CP037) == "true"){
                            setTimeout(function(){
                                _self.fireEvent("exit");
                            },_public.cp037TimeOut);
                        } else{
                            _self.showSuccess(waf.wafEdit.info.submitsuccess);
                            _self._pageInitByModelLoad(data)
                        }
                    }
                }
            }
				,
            error: function(strErrorSummary,strErrorDetail){
                _public.budgetmaybechange = false;
                _private.checkBudgetError(strErrorSummary,strErrorDetail);
                result = false;
            }
			});
            return result;
        }
    	,
        checkBudgetSuccess: function(data) {
            //exception
            var model = _self.getCurrentModel(),
                    budgetDescription = waf("#budgetDescription");
            if(data != null && data.message && (String(data.isBudgetPass) == "true")) {
                model.isOverBudget = 1;
                if (waf("#section_budgetDescription").is(":visible")) return;
                if(!bizCollBillBase.isNeedBudgetDescription()) return;
                var msgs = data.message.split("!");
                var magdiv = '';
                for(var i = 0; i < msgs.length; i++) {
                    var msg = msgs[i];
                    magdiv += "<li><span>" + msg + "</span></li>";
                }
                // 删除之前创建的提示框div,防止重复创建导致取数错误
                if ($("#bgMainContentDiv").length != 0) $("#bgMainContentDiv").remove();
                var contentDiv = $("<div class='bgDiv' style='margin-left: -155px;'></div>").append(magdiv);
                contentDiv = $("<div id='bgMainContentDiv'></div>").append(contentDiv);
                var footerDiv = "<div class='bgFootDiv' style='margin-left: -155px;'><span>"+_self._localeStr.WRITE_BUDGET_EXCESS_INSTRUCTIONS+"</span></div>";
                contentDiv = $(contentDiv).append(footerDiv);
                var bgDescription = $("<textarea id='bgOverDescription'/>").val(budgetDescription.val());
                bgDescription = $("<div class='bgDescriptionDiv'></div>").append(bgDescription);
                contentDiv = $(contentDiv).append(bgDescription);
                waf.msgBox.showConfirm({
                        title: _self._localeStr.BUDGET_EXCESS_PROMPT,
                        width: "450px",
                        target: contentDiv,
                        buttons:[{
                    text: _self._localeStr.OK,
                            id : "biz-ok"
                }]
				});
                waf("#biz-ok").unbind().bind("click",function(bgMainContentDiv){
                    budgetDescription.wafTextarea("setValue", waf("#bgOverDescription").val());
                    budgetDescription.wafValidator("option","errMsg","required:'"+_self._localeStr.BEYOND_BUDGET_STATE_NOT_NULL+"'");
                    budgetDescription.wafValidator("option","rules","required:true");
                    if(waf("#bgOverDescription").val().length>0){
                        waf("#section_budgetDescription").wafSection("option", "hidden", false);
                        //属性选择器来进行影藏 bug_number =BT1115958
                        //$('[aria-labelledby="ui-dialog-title-bgMainContentDiv"]').remove();
                        $("#bgMainContentDiv").remove();
                        if(!(bizCollBillBase.isFromWFSubmitWeb || bizCollBillBase.isFromSSCSubmitWeb)){
                            //审批配编辑页面时，不需要这里调用submit, 通过流程中的提交按钮触发submit
                            _private.submit();
                        }
                    }else{
                        $(bgOverDescription).css({
                                "border-bottom-color":"red"
							/*"border-color":"red",
							"border-width": "0 0 1px 0"*/
						});
                    }
                });
            }else{
                model.isOverBudget = 0;
                budgetDescription.wafTextarea("setValue",null);
                model.budgetDescription = null;
                waf("#section_budgetDescription").hide();
                budgetDescription.wafValidator("option","rules","required:false");
            }
        }
    	,
        checkBudgetError: function(strErrorSummary,strErrorDetail) {
            var model = _self.getCurrentModel(),
                    budgetDescription = waf("#budgetDescription");
            model.isOverBudget = 0;
            model.budgetDescription = null;
            budgetDescription.wafTextarea("setValue", null);
            _self.showError(strErrorSummary, strErrorDetail);
        }
    	,
        closeBillPage: function() {
            // 关闭当前页
            if (!top.opener) {
                if (waf.isInFramePage()) {
                    waf.closeCurPageTab();
                } else {
                    waf.window.close();
                }
            } else {
                window.close();
            }
        }
    	,
        doAppendReqRow: function(datas) {
            // 新增冲销借款
            var reqGridDom = waf("#reqCheckEntries");
            var selectedRowId = reqGridDom.wafGrid("getSelectedRow");
            for (var i = 0, il = datas.length; i < il; i++) {
                var tmpData = datas[i];
                var data = {
                        sourceBillNumber: tmpData.number,
                        sourceBillDate: tmpData.bizReqDate,
                        sourceBillCause: tmpData.cause,
                        sourceBillAmountApproved: tmpData.amountApproved,
                        sourceBillOperationType: tmpData.operationType,
                        sourceBillExpenseType: tmpData.expenseType,
                        sourceBillAmountBalance: tmpData.amountBalance,
                        sourceBillID: tmpData.sourceBillId,
                        sourceBillEntryID: tmpData.entryid,
                        sourceBillExpenseTypeId: tmpData.sourceBillExpenseTypeId,
                        // 非model中的字段,这里只能全部小写
                        sourcebillexpensetypedisplay: tmpData.expenseTypeDisplayName,
                        sourceBillCostCenterId: tmpData.sourceBillCostCenterId,
                        sourceBillCostCenterName: tmpData.sourceBillCostCenterName,
                        hasSourceBill: tmpData.hasSourceBill,
                        checkAmount: tmpData.amountBalance,
                        checkAmountOri: tmpData.amountBalanceOri,
                        exchangeRate: tmpData.exchangeRate,
                        convertMode: tmpData.convertMode,
                        currencyType: {id: tmpData.currencyTypeId, name: tmpData.currencyTypeName},
                exchangeRatePrecision: tmpData.exchangeRatePrecision,
                        sourceBillAmountBalanceOri: tmpData.amountBalanceOri,
                        sourceBillProjectId: tmpData.sourceBillProjectId,
                        sourceBillProjectName: tmpData.sourceBillProjectName
				};
                //reqGridDom.wafGrid("addRow", {data: data, position: "last"});
                if(_public.rowAddType =="add"){
                    reqGridDom.wafGrid("addRow", {data: data, position: "last"});
                }else{//插行
                    reqGridDom.wafGrid("addRow", {data: data, position: "before",src: selectedRowId});
                }
            }
            // 刷新核销金额
            _public.refreshCheckAmountForAll();
            // 刷新合计金额
            _public.refreshTotalAmount("reqCheckEntries", "reqAmount", "checkAmount");
            // 隐藏分录 | 冲销借款 | 收款人分录的浮动工具条
            waf("#entries, #loanCheckEntries, #editGrid_collection").wafGrid("hideFloatBar");
            // 刷新多币别合计
            bizCollBillBase.refreshCurrencyTotal("reqCheckEntries", "checkAmountOri",
                    "checkAmount", "reqCheckCurrencyTotalText", _self._localeStr.WRITE_OFFS, _public.multiCurrencyId);
        }
    	,
        doSelectPayer: function(data) {
            // 选择收款人回调处理
            if (!data) return;
            //收款信息对应的银行状态必须为启用才可以选择
            if(data["Bebank.State"] && data["Bebank.State"] == 2){
                _self.showWarning(_self._localeStr.BEBANKSTATENOTCLOSE);
                return false;
            }
            waf("#payerName").wafText("setValue", data.payee);
            waf("#payerAccount").wafText("setValue", data.bankAccount);
            var beBank = {name: data.BebankStr, id: data["Bebank.id"], state: data["Bebank.State"]};
            waf("#payerBankF7").wafPromptBox("setValue", beBank);
            var openArea = {id: data["openArea.id"], name: data["openArea.name"]};
            waf("#openArea").wafPromptBox("setValue", openArea);
            waf("#payerBank").wafText("setValue", data.BebankStr);
        }
    	,
        checkBudget: function(model) {
            var editModel = waf.toJSONString(model),
                    result = false,
                    budgetDescription = waf("#budgetDescription");
            waf.invokeController({
                    url: "/cp/bc/bill.do?method=checkBudget",
                    data: {model:editModel},
            async: false,
                    success: function(data){
                //exception
                if(String(data.isBudgetPass) == "false" && _public.isWarnOverBudget()){
                    model.isOverBudget = 0;
                    budgetDescription.wafTextarea("setValue",null);
                    model.budgetDescription = null;
                    result = true;
                    waf("#msgArea1").wafMsgArea("show", {type: "error", summaryMsg: data.message});
                    return;
                }
                if(data != null && data.message && (String(data.isBudgetPass) == "true")) {
                    model.isOverBudget = 1;
                    if (waf("#section_budgetDescription").is(":visible")) return;
                    if(!bizCollBillBase.isNeedBudgetDescription()) return;
                    result = true;
                    var msgs = data.message.split("!");
                    var magdiv = '';
                    for(var i = 0; i < msgs.length; i++) {
                        var msg = msgs[i];
                        msg = msg.replace(/\[/g, "<span class='bg_red'>[").replace(/\]/g, "]</span>");
                        magdiv += "<li><span>" + msg + "</span></li>";
                    }
                    // 删除之前创建的提示框div,防止重复创建导致取数错误
                    if ($("#bgMainContentDiv").length != 0) $("#bgMainContentDiv").remove();
                    var contentDiv = $("<div class='bgDiv'></div>").append(magdiv);
                    contentDiv = $("<div id='bgMainContentDiv'></div>").append(contentDiv);
                    var footerDiv = "<div class='bgFootDiv'><span>"+_self._localeStr.WRITE_BUDGET_EXCESS_INSTRUCTIONS+"</span></div>";
                    contentDiv = $(contentDiv).append(footerDiv);
                    var bgDescription = $("<textarea id='bgOverDescription'/>").val(budgetDescription.val());
                    bgDescription = $("<div class='bgDescriptionDiv'></div>").append(bgDescription);
                    contentDiv = $(contentDiv).append(bgDescription);
                    waf.msgBox.showConfirm({
                            title: _self._localeStr.BUDGET_EXCESS_PROMPT,
                            width: "450px",
                            target: contentDiv,
                            buttons:[{
                        text: _self._localeStr.OK,
                                click: function() {
                            waf("#section_budgetDescription").show();
                            budgetDescription.wafTextarea("setValue", waf("#bgOverDescription").val());
                            budgetDescription.wafValidator("option","errMsg","required:'"+ _self._localeStr.BEYOND_BUDGET_STATE_NOT_NULL+"'");
                            budgetDescription.wafValidator("option","rules","required:true");
                        }
                    }]
						});
                }else{
                    model.isOverBudget = 0;
                    budgetDescription.wafTextarea("setValue",null);
                    model.budgetDescription = null;
                    waf("#section_budgetDescription").hide();
                    budgetDescription.wafValidator("option","rules","required:false");
                }
            },
            error: function(){
                model.isOverBudget = 0;
                budgetDescription.wafTextarea("setValue",null);
                model.budgetDescription = null;
            }
			});
            return result;
        }
        // ----END 删除
    	,
        collectionGridCellSave: function(rowid, cellname, value, iRow, iCol, oldValue) {
            if (value == oldValue) return;
            var editGridDom = waf("#editGrid_collection"),
                    collectionRows = editGridDom.wafGrid("getAllRowData");
            if (cellname == "payerType") {
                editGridDom.wafGrid("setCell", rowid, "payerName", null);
                editGridDom.wafGrid("setCell", rowid, "payerNameEntryF7", null);
                waf("#collectionEntryF7").wafPromptBox("setValue", null);
                editGridDom.wafGrid("setCell", rowid, "payerBank", null);
                editGridDom.wafGrid("setCell", rowid, "openArea", null);
                waf("#entryPayerBankF7").wafPromptBox("setValue", null);
                editGridDom.wafGrid("setCell", rowid, "payerAccount", null);
                waf("#payerAccountF7").wafPromptBox("setValue", null);
                waf("#entryPayerAccountF7").wafPromptBox("setValue", null);
                editGridDom.wafGrid("setCell", rowid, "isPublicAccount", null);

                waf("#savePayerInfoCheck").wafCheckbox("setChecked",false);
                if (value == "10") {
                    waf("#savePayerInfoCheck").wafCheckbox("option", "hidden", false);
                    waf("#savePayerInfoCheck").wafCheckbox("enable");
                } else {
                    waf("#savePayerInfoCheck").wafCheckbox("disable");
                    waf("#savePayerInfoCheck").wafCheckbox("option", "hidden", true);
                }

            } else if (cellname == "currencyType"){
                if(value != null && (_public.currencyMap.containsKey(value.id) || _public.colCurrencyMap.containsKey(value.id))){
                    if(_public.currencyMap.get(value.id)!=null){
                        var object = _public.currencyMap.get(value.id);
                    }else{
                        var object = _public.colCurrencyMap.get(value.id);
                    }
                    // 分录-汇率精度赋值
                    editGridDom.wafGrid("setCell", rowid, "exchangeRatePrecision", object.exchangeRatePrecision);
                    // 分录-汇率的精度设置
                    editGridDom.wafGrid("setCellEditorAllConfig", "exchangeRate", "decimalPrecision", object.exchangeRatePrecision, "numberField",rowid);
                    // 分录-汇率赋值
                    editGridDom.wafGrid("setCell", rowid, "exchangeRate", object.exchangeRate);
                    // 分录-折算方式赋值
                    editGridDom.wafGrid("setCell", rowid, "convertMode", object.convertMode);
                    var encashedAmount = waf("#encashedAmount").wafNumberField("getValue");
                    var amountOriSum =  _self.setPrecision(waf("#entries").wafGrid("getColValue", "amountOri", false, "sum"));
                    //重新计算金额
                    bizCollBillBase.refreshCollectionEntryAmount(rowid, encashedAmount, amountOriSum);
                }else{
                    // 分录-汇率精度赋值
                    editGridDom.wafGrid("setCell", rowid, "exchangeRatePrecision", 4);
                    // 分录-汇率的精度设置
                    editGridDom.wafGrid("setCellEditorAllConfig", "exchangeRate", "decimalPrecision", 4, "numberField",rowid);
                    // 分录-汇率赋值
                    editGridDom.wafGrid("setCell", rowid, "exchangeRate", 0);
                    // 分录-折算方式赋值
                    editGridDom.wafGrid("setCell", rowid, "convertMode", 0);
                    editGridDom.wafGrid("setCell", rowid, "amountOri", 0);
                    editGridDom.wafGrid("setCell", rowid, "amount", 0);
                }
                // 差额补到收款明细
                bizCollBillBase.balanceEncashedAmountAndCollectionAmount();
            } else if (cellname == "amountOri"){
                if(value == null) value = 0;
                var amountOri = value;
                var exchangeRate = editGridDom.wafGrid("getCell", rowid, "exchangeRate");
                var convertMode = editGridDom.wafGrid("getCell", rowid, "convertMode");
                var amount = _self.computeAmount(amountOri, convertMode, exchangeRate);
//				var amount = bizCollBillBase.ifConvertMode(convertMode) ? _self.divideBigDecimal(amountOri,exchangeRate)
//						: _self.multiplyBigDecimal(amountOri,exchangeRate);
                editGridDom.wafGrid("setCell", rowid, "amount", _self.setPrecision(amount));
                // 差额补到收款明细
                bizCollBillBase.balanceEncashedAmountAndCollectionAmount();
            }
            // 刷新多币别合计
            editGridDom.wafGrid("calcFooterData");
            bizCollBillBase.refreshCurrencyTotal("editGrid_collection", "amountOri",
                    "amount", "collectionCurrencyTotalText", _self._localeStr.AMOUNT_TO_PAY, _public.multiCurrencyId);
        }
    	,
        save: function() {
            // 重写保存事件
            // 设置不二次刷新
            _self.setOnlyModelLoad(true);
            //从附件控件中获取到附件个数，并赋值附件数文本控件
            //_private.setAccessoryCount();
            _self.fireEvent("fieldOnStoreEvent");
            _public.isLoanAccount=false;
            var modelInfo = _self.getCurrentModel();
            if (waf("#savePayerInfoCheck").length > 0) modelInfo.savePayerInfo = waf("#savePayerInfoCheck").wafCheckbox("option", "checked");
            //去掉amount为空或0的分录
            bizCollBillBase.checkModel(modelInfo);
            var editModel = waf.toJSONString(modelInfo);
            // TODO 提交触发校验后,再点击保存手动隐藏校验信息.
            bizCollBillBase.resetValidate();

            var option = {};
            option.action = "save";
            option.data = {model:editModel, operateState: _self.operateState};
            option.success = function(objectPK) {
                _self.showSuccess(waf.wafEdit.info.savesuccess);
                if (!_self.getOnlyModelLoad()) {
                    _self.getCurrentModel().id = objectPK;
                    var curWinTarget = waf.getUrlParams(document.location.href).curWinTarget;
                    var targetUrl = _self.getDynamicContextPath()+"?method=doEvent&uipk=" + (_self.getEditUrl()?_self.getEditUrl():_self.getUIPk())
                            + "&event=initialize&operateState=EDIT" + "&billId=" + waf.encodeURIComponent(objectPK)
                            + "&curWinTarget=" + curWinTarget;
                    if (_self.getQueryPK()!=null&&_self.getQueryUuid()!=null) {
                        targetUrl += "&queryPK=" + _self.getQueryPK() + "&queryUuid="+waf.encodeURIComponent(_self.getQueryUuid())
                                + "&sequenceNo=" + _self.getSequenceNo() + "&billTotal=" + _self.getBillTotal();
                    }
                    targetUrl = _self.addEASMutilOrgParam(targetUrl);
                    document.location.href = targetUrl;
                } else {
                    _self._pageInitByModelLoad(objectPK);
                }
            };
            waf.doPost(option);
        }
    	,
        submit:function(e){
            // 重写基类提交事件
            //	_private.setAccessoryCount();
            _self.fireEvent("fieldOnStoreEvent");
            var formValidate = waf("#form").wafFormValidator("validateForm", true);
            if (!formValidate) return false;
//			var gridError = waf("#entries").wafGrid("getErrorInput");
//			var collectionGridError = waf("#editGrid_collection").wafGrid("getErrorInput");
//			if (!formValidate || (gridError && gridError.length > 0) || (collectionGridError && collectionGridError.length >0)) {
//				return;
//			}

            var model = _self.getCurrentModel(),
                    modelInfo = waf.cloneObject(model);
            // TODO 去掉_self.processModelBeforeSubmit
            if (!modelInfo.entries || modelInfo.entries.length == 0) {
                _self.showError(_self._localeStr.AT_LEAST_ONE_ENTRY);
                return false;
            }
            if (waf("#savePayerInfoCheck").length > 0) modelInfo.savePayerInfo = waf("#savePayerInfoCheck").wafCheckbox("option", "checked");
            //为true表示流程中心或者共享中心审批节点配编辑页面，model需要设置审批配编辑标识,调后台submit完成校验和反写。
            if (bizCollBillBase.isFromWFSubmitWeb || bizCollBillBase.isFromSSCSubmitWeb) {
                modelInfo.isFromWFSubmitWeb = true;//model设置web端审批配编辑标识
                bizCollBillBase.initApprovedAmount(modelInfo);//初始化分录核定金额
            }
            var urlParam = waf.getUrlParams(document.location.href);
            var isApproved = urlParam.UIStatus == "amountApproved" || urlParam.operateState == "amountApproved" || urlParam.UIStatus == "sscApproved" || urlParam.operateState == "sscApproved"? true : false;
            modelInfo.isApproved = isApproved;
			/*if (urlParam["isFromWorkflow"] == "false" || urlParam["isFromWorkflow"] == null) {
				modelInfo.state = {isenum: true, alias: _self._localeStr.SUBMITTED, value: 25};
			}*/
            if(modelInfo.state && (modelInfo.state.value < 25 || modelInfo.state.value == 40)) modelInfo.state = {isenum: true, alias: _self._localeStr.SUBMITTED, value: 25};
            modelInfo.budgetmaybechange = _public.budgetmaybechange;
            //检查model的checkAmount以及多收款分录的amountOri
            bizCollBillBase.checkModel(modelInfo);
            var editModel = waf.toJSONString(modelInfo);
            return _private.submitFunc(editModel);
        }
    	,
        abandon: function() {
            waf.msgBox.showConfirm({
                    autoBtnClose: false,
                    summaryMsg:  _self._localeStr.ARE_YOU_SURE_ABANDON,
                    buttonType: "ok-cancel",
                    buttonCallBack: [function(dialog) {
                waf.msgBox.close(dialog);
                var model = _self.getCurrentModel();
                waf.doPost({
                        action: "abandon",
                        data: {id: model.id},
                success: function(result) {
                    if ("success" == result) {
                        waf.msgBox.showInfo({
                                summaryMsg:  _self._localeStr.ABANDONED_SUCCESS,
                                buttonType: "ok",
                                buttonCallBack: [function(dialog) {
                            _private.closeBillPage();
                        }]
								});
                    }
                }
					});
            }]
			});
        }
    	,
        entriesGridAddRow:function(){
            if(bizCollBillBase.isTicketInvoice()){
                waf.doPost({
                        action: "appendRow",
                        //必须同步，保证addrow创建的dom在设置控件属性之前
                        async:false,
                        data: {entryKey: "entries"},
                success: function(result) {
                    if(result.id)_public.entryInitData.id = result.id;
                    waf("#entries").wafGrid("addRow", {data: _public.entryInitData});
                }
    			});
            }else{
                waf("#entries").wafGrid("addRow", {data: _public.entryInitData});
            }
        }
    	,
        loanGridAddRow: function(type) {
            // 冲销借款新增行
            _public.rowAddType = type;
            var selectedRowId = waf("#loanCheckEntries").wafGrid("getSelectedRow");
            if(type=="insert"&&!selectedRowId){
                _self.showWarning(waf.wafEdit.info.selectRow);
                return;
            }
            var applierId = bizCollBillBase.getApplierId();
            var applierCompany = waf("#applierCompany").wafPromptBox("getValue");
            var companyId = null;
            if(applierCompany) {
                companyId = applierCompany.id;
            }
            if (!applierId) {
                waf.msgBox.showInfo({summaryMsg:_self._localeStr.PROPOSER_NOT_NULL, buttonType:"ok"});
                return;
            }
            var orgUnit = waf("#orgUnit").wafPromptBox("getValue"),
                    orgUnitId = (orgUnit && orgUnit.id) || null;
            if (!orgUnitId) {
                waf.msgBox.showInfo({summaryMsg:_self._localeStr.PROPOSER_ORGUNIT_NOT_NULL, buttonType:"ok"});
                return;
            }
            var sourceBillEntryIds = waf("#loanCheckEntries").wafGrid("getColValue", "sourceBillEntryID");
            var winStyle = bizCollBillBase.getCheckWinStyle();
            winStyle.title = _self._localeStr.TO_REVERSE_THE_BORROWING;
            winStyle.width = 1045;
            winStyle.height = 550;
            winStyle.url = waf.getContextPath() + "/dynamicList.do?event=initialize&method=initalize&waf2skin=easbase"
                    + "&uipk=com.kingdee.eas.cp.bc.CheckEntriesList";
            winStyle.data = {applier: applierId, billType: "BizAccountOutBill", sourceBillType: "dailyLoan",
                    selectedIds: waf.toJSONString(sourceBillEntryIds), companyId: companyId, orgUnit: orgUnitId};
            winStyle.retValCallBack = _private.doAppendLoanRow;
            waf.window.open(winStyle);
        }
    	,
        entryGridDelRow:function(rowId,rowObj){
            //与cp039集成，且分录关联了发票，提示客户是否确定删除
            if(bizCollBillBase.isTicketInvoice() && rowObj.ticketUrl){
                //为true表示流程中心或者共享中心审批节点配编辑页面，无法删除带发票的分录
                if(bizCollBillBase.isFromWFOrSSC()){
                    _self.showWarning(_self._localeStr.CANNOT_DELETE_TICKET);
                    return ;
                }
                waf.msgBox.showConfirm({
                        autoBtnClose: false,
                        summaryMsg: _self._localeStr.ARE_YOU_SURE_DELETE_TICKET,
                        buttonType: "ok-cancel",
                        buttonCallBack: [function(dialog) {
                    _self.fireEvent("fieldOnStoreEvent");
                    waf.msgBox.close(dialog);
                    waf.doPost({
                            action: "delInvoice",
                            data: {
                        id:_self.getCurrentModel().id.toString(),
                                model:waf.toJSONString(_self.getCurrentModel()),
                                userKey:_public.userKeyJson,
                                applierCompanyId:_self.getCurrentModel().applierCompany.id.toString(),
                                entryId:rowId
                    },
                    async: false,
                            success: function(result) {
                        _public.delInvoiceClose(result);
                        //成功后，才去删除对应的分录，否则就提示错误，不改变界面分录信息
                        _private.showBgData("expenseType", null, false, rowId, true);
                        //改变超预算说明
                        _private.changeBudgetDescription(false);
                        waf("#entries").wafGrid("delRow", rowId);
                        //删除后 重置币别缓存
                        _private.updateCurrencyMap();
                        _public.refreshTotalAmount("entries", "amount", "amount", true);
                    },
                    error:function(result){
                        _self.showError(result);
                        console.log("del entry error "+result);
                    }
 						});
                }, function() {}]
 				});
            }else{
                _private.showBgData("expenseType", null, false, rowId, true);
                //改变超预算说明
                _private.changeBudgetDescription(false);
                waf("#entries").wafGrid("delRow", rowId);
                //删除后 重置币别缓存
                _private.updateCurrencyMap();
                _public.refreshTotalAmount("entries", "amount", "amount", true);
                //刷新model，否则会造成删除之后，在取值发现model里面还有删除以后的分录
                _self.fireEvent("fieldOnStoreEvent");
            }
        }
    	,
        loanGridDelRow:function(e){
            // 冲销借款删除行
            var loanGridDom = waf("#loanCheckEntries")
            var rowid =  loanGridDom.wafGrid("getSelectedRow");
            if (!rowid){
                _self.showMessage(_self._localeStr.PLEASE_SELECT_ROW);
                return;
            }
            loanGridDom.wafGrid("delRow", rowid);
            // 刷新核销金额
            _public.refreshCheckAmountForAll();
            // 重新计算冲销借款金额
            _public.refreshTotalAmount("loanCheckEntries", "loanAmount", "checkAmount");
            // 隐藏分录 | 冲销申请 | 收款人分录的浮动工具条
            waf("#entries, #reqCheckEntries, #editGrid_collection").wafGrid("hideFloatBar");
            // 刷新多币别合计
            bizCollBillBase.refreshCurrencyTotal("loanCheckEntries", "checkAmountOri",
                    "checkAmount", "loanCheckCurrencyTotalText", _self._localeStr.WRITE_OFFS, _public.multiCurrencyId);
        }
	    ,
        reqGridAddRow:function(type){
            // 冲销申请分录新增行
            _public.rowAddType = type;
            var selectedRowId = waf("#reqCheckEntries").wafGrid("getSelectedRow");
            if(type=="insert"&&!selectedRowId){
                _self.showWarning(waf.wafEdit.info.selectRow);
                return;
            }
            var applierId = bizCollBillBase.getApplierId();
            var applierCompany = waf("#applierCompany").wafPromptBox("getValue");
            var companyId = null;
            if(applierCompany) {
                companyId = applierCompany.id;
            }
            if (!applierId) {
                waf.msgBox.showInfo({summaryMsg:_self._localeStr.PROPOSER_NOT_NULL, buttonType:"ok"});
                return;
            }
            var sourceBillEntryIds = waf("#reqCheckEntries").wafGrid("getColValue", "sourceBillEntryID");
            var winStyle = bizCollBillBase.getCheckWinStyle();
            winStyle.title = _self._localeStr.TO_BE_WRITTEN_APPLICATION;
            winStyle.width = 850;
            winStyle.height = 550;
            winStyle.url = waf.getContextPath() + "/dynamicList.do?event=initialize&method=initalize&waf2skin=easbase"
                    + "&uipk=com.kingdee.eas.cp.bc.CheckEntriesList";
            winStyle.data = {applier: applierId, billType: "BizAccountOutBill", sourceBillType: "otherExpense",
                    selectedIds: waf.toJSONString(sourceBillEntryIds)};
            winStyle.retValCallBack = _private.doAppendReqRow;
            waf.window.open(winStyle);
        }
	    ,
        reqGridDelRow:function(e){
            // 冲销申请删除
            var reqCheckGridDom = waf("#reqCheckEntries")
            var rowid =  reqCheckGridDom.wafGrid("getSelectedRow");
            if (!rowid){
                _self.showMessage(_self._localeStr.PLEASE_SELECT_ROW);
                return;
            }
            reqCheckGridDom.wafGrid("delRow", rowid);
            // 刷新核销金额
            _public.refreshCheckAmountForAll();
            _public.refreshTotalAmount("reqCheckEntries", "reqAmount", "checkAmount");
            // 隐藏分录 | 冲销借款 | 收款人分录的浮动工具条
            waf("#entries, #loanCheckEntries, #editGrid_collection").wafGrid("hideFloatBar");
            // 刷新多币别合计
            bizCollBillBase.refreshCurrencyTotal("reqCheckEntries", "checkAmountOri",
                    "checkAmount", "reqCheckCurrencyTotalText", _self._localeStr.WRITE_OFFS, _public.multiCurrencyId);
        }
	    ,
        editGridAddRow:function(e){
            // 分录表格新增行
            var rowid = $(e.target).closest(".rowoverlay").attr("rowid");
            if (rowid == "") {
                waf("#entries").wafGrid("addRow", {data: _public.entryInitData});
            } else {
                waf("#entries").wafGrid("addRow", {src: rowid, data: _public.entryAddCopyData(rowid), position: "after"});
            }
            waf("#entries").wafGrid("setFloatActions", [{"remove": true, onclick: _private.editGridDelRow}]);
            // 隐藏冲销借款| 冲销申请 | 收款人分录的浮动工具条
            waf("#loanCheckEntries, #editGrid_collection, #reqCheckEntries").wafGrid("hideFloatBar");
        }
	    ,
        editGridDelRow:function(e){
            // 分录表格删除行
            var rowid = $(e.target).closest(".rowoverlay").attr("rowid");
            if (!rowid) return;
            //加载最新的model到界面
            _self.fireEvent("fieldOnStoreEvent");
			/*// cpbcinvoice
			waf.doPost({
                action: "delInvoice",
                data: {
					id:_self.getCurrentModel().id.toString(),
					model:waf.toJSONString(_self.getCurrentModel()),
					userKey:_public.userKeyJson,
					applierCompanyId:_self.getCurrentModel().applierCompany.id.toString(),
                	entryId:rowid
                },
                async: false,
                success: function(result) {
                	_public.delInvoiceClose(result);
                },
				error:function(result){
                    alert(result);
                }
            });*/

            waf("#entries").wafGrid("delRow", rowid);
            //删除后 重置币别缓存
            _private.updateCurrencyMap();
            _public.refreshTotalAmount("entries", "amount", "amount", true);
            // 隐藏冲销借款| 冲销申请 | 收款人分录的浮动工具条
            waf("#loanCheckEntries, #editGrid_collection, #reqCheckEntries").wafGrid("hideFloatBar");
        }
	    ,
        collectionGridAddRow:function(type){
            // 多收款人新增行
            var collectionEntryDom = waf("#editGrid_collection"),
                    entryDom = waf("#entries"),
                    currentRowId =  waf("#editGrid_collection").wafGrid("getSelectedRow"),
                    model = _self.getCurrentModel();
            if (model.currencyType && _public.currencyMap.containsKey(model.currencyType.id)) {
                var object = _public.currencyMap.get(model.currencyType.id);
                _public.collectionEntryInitData.exchangeRate = object.exchangeRate;
                _public.collectionEntryInitData.convertMode = object.convertMode;
                _public.collectionEntryInitData.precision = object.exchangeRatePrecision;
            }
			/*if (!currentRowId) {
				collectionEntryDom.wafGrid("addRow", {data: _public.collectionEntryInitData});
			} else {
				collectionEntryDom.wafGrid("addRow", {data: _public.collectionEntryInitData,
					position: "after", src: currentRowId});
			}*/
            _public.collectionEntryInitData.currencyType = model.currencyType;
            var entryInitData = waf.cloneObject(_public.collectionEntryInitData);
            if (!collectionEntryDom.wafGrid("getDataIDs").length) {
                var amount = waf("#encashedAmount").wafNumberField("getValue") || 0;
                entryInitData.amount =  amount;
                entryInitData.amountOri = amount;
            }

            if (type=="add") {
                var currencyTypes = entryDom.wafGrid("getColValue", "currencyType");
                var hasMoreCurrency = false;
                for (var i = 0, il = currencyTypes.length - 1; i < il; i++) {
                    if (!currencyTypes[i] || !currencyTypes[i + 1]
                            || currencyTypes[i].id != currencyTypes[i + 1].id) {
                        hasMoreCurrency = true;
                        break;
                    }
                }

                if (!currencyTypes[0]) hasMoreCurrency = true;
                if (!hasMoreCurrency) {
                    var rowData = entryDom.wafGrid("getRowData", entryDom.wafGrid("getDataIDs")[0]);
                    entryInitData.currencyType = rowData.currencyType;
                    entryInitData.exchangeRate = rowData.exchangeRate;
                    entryInitData.convertMode = rowData.convertMode;
                    entryInitData.exchangeRatePrecision = rowData.exchangeRatePrecision;
                    if(collectionEntryDom &&collectionEntryDom.length==0){
                        //计算原币金额
                        if(bizCollBillBase.isCollectionSameWithEntryCurrency(entryInitData.currencyType, entryInitData.exchangeRate) && waf("#loanCheckEntries").wafGrid("getAllRowData").length == 0){
                            entryInitData.amountOri =  waf("#entries").wafGrid("getColValue", "amountOri", false, "sum");
                        } else{
                            //不同币别/有冲借款时，根据汇率用本位币计算原币金额
                            entryInitData.amountOri = bizCollBillBase.ifConvertMode(entryInitData.convertMode) ? entryInitData.amount * entryInitData.exchangeRate : entryInitData.amount /entryInitData.exchangeRate;
                        }
                    }
                }
                collectionEntryDom.wafGrid("addRow", {data: entryInitData});
            } else {
                if(currentRowId){
                    var rowData = collectionEntryDom.wafGrid("getRowRealData", currentRowId);
                    entryInitData.currencyType = rowData.currencyType;
                    entryInitData.exchangeRate = rowData.exchangeRate;
                    entryInitData.convertMode = rowData.convertMode;
                    entryInitData.exchangeRatePrecision = rowData.exchangeRatePrecision;
                    collectionEntryDom.wafGrid("addRow", {data: entryInitData, position: "before", src: currentRowId});
                }else{
                    _self.showWarning(waf.wafEdit.info.selectRow);
                }

            }
            collectionEntryDom.wafGrid("calcFooterData");
        }
	    ,
        collectionGridDelRow:function(e){
            // 多收款人删除行
            var collectionGridDom = waf("#editGrid_collection");
            var rowid =  collectionGridDom.wafGrid("getSelectedRow");
            if (!rowid){
                _self.showMessage(_self._localeStr.PLEASE_SELECT_ROW);
                return;
            }
            collectionGridDom.wafGrid("delRow", rowid);
            //刷新收款金额
            _public.initCollectionGrid();
            // 刷新多币别合计
            collectionGridDom.wafGrid("calcFooterData");
            bizCollBillBase.refreshCurrencyTotal("editGrid_collection", "amountOri",
                    "amount", "collectionCurrencyTotalText", _self._localeStr.AMOUNT_TO_PAY, _public.multiCurrencyId);
        }
	    ,
        validateEncashedAmount:function(value, dom){
            // 付现金额合法性校验
            var msg = _self._localeStr.CASH_NOT_LESS_THAN_ZERO;
            if (Number(value) >= 0) {
                return;
            }
            return msg;
        }
	    ,
        validateCollectionAmount:function(value,dom){
            // 收款金额合法性校验
            var msg = _self._localeStr.AMOUNT_COLLECTED_THAN_ZERO;
            if (Number(value) > 0) {
                return;
            }
            return msg;
        }
	    ,
        validateEntryOpertype:function(value,dom){
            // 分录-业务类别校验
            //if ($(dom).wafPromptBox("getValue").isEnable) return;
            return;
        }
	    ,
        validateEntryExpType:function(value,dom){
            // 分录-费用类型校验
            if (!$(dom).wafPromptBox("getValue")) return _self._localeStr.EXPENSETYPE_NOT_NULL;
            //if ($(dom).wafPromptBox("getValue").isStart) return;
            return;
        }
    	,
        validateAmount:function(value, dom){
            // 合计金额合法性校验
//			if (Number(value) <= 0) {
//				return _self._localeStr.AMOUNT_MUST_BE_THAN_ZERO;;
//			}
        }
    	,
        bizCollBillDel:function(e){
            // 重写框架删除事件
            var billId = _self.getCurrentModel().id;
            waf.msgBox.showConfirm({
                    autoBtnClose: false,
                    summaryMsg: _self._localeStr.ARE_YOU_SURE_DELETE,
                    buttonType: "ok-cancel",
                    buttonCallBack: [function(dialog) {
                waf.msgBox.close(dialog);
                waf.doPost({
                        action: "delete",
                        param: "modelID=" + waf.encodeURIComponent(billId.toString()),
                        success: function(data) {
                    if(!data.exceptions[0]){
                        waf.msgBox.showInfo({
                                summaryMsg: _self._localeStr.DELETED_SUCCESSFULLY,
                                buttonType: "ok",
                                buttonCallBack: [function(dialog) {
                            if (waf.isInFramePage()) {
                                waf.closeCurPageTab();
                            } else {
                                waf.window.close();
                            }
                            _self.destroyAction();
                        }]
								});
                    }else{
                        waf.msgBox.showError({
                                summaryMsg: data.exceptions[0].message,
                                buttonType: "ok",
								});
                    }
                }
					});
            }]
			});
        }
	    ,
        viewLoanPay:function(e){
            bizCollBillBase.viewLoanPay();
        }
    	,
        viewBudget:function(e){
            bizCollBillBase.viewBudget();
        }
	    ,
        viewImage:function(e){
            var urlParam = waf.getUrlParams(document.location.href);
            var isCanRescan = urlParam.isFromWorkflow;
            bizCollBillBase.viewImage(isCanRescan);
        }
	    ,
        reportCover:function(e){
            bizCollBillBase.reportCover(_public.reportCoverQueryPK, _public.reportCoverTemplate);
        }
	    ,
        billReport:function(e){
            _self._reportActionEventHandler();
        }
	    ,
        editGridRowAdd: function(rowid,rowdata,rowelem) {
            // 分录表格行新增时,初始化费用支付公司过滤
            var editGridDom = waf("#entries"),
                    initData = bizCollBillBase.getModelInitData(),
                    operationType = rowdata.operationType;

            // 业务类别F7过滤
            var operationTypeOptions = editGridDom.wafGrid("getCellConfig", "operationType", rowid).editoptions;
            operationTypeOptions.subTagJson.filteritem = _private.getOperationTypeFilter;
            editGridDom.wafGrid("setCellConfig", "operationType", "editoptions", operationTypeOptions, rowid);
            // 费用类型F7设置
            var editoptions = editGridDom.wafGrid("getCellConfig", "expenseType", rowid).editoptions;
            editoptions.subWidgetName = "wafPromptExpenseType";
            editoptions.subTagJson = {billType: "BizAccountOutBill",width: 960,height: 600,
                    companyId: _public.getCompanyId, operationTypeId: _public.getOperationTypeId,
                    costCenterId: _public.getCostCenterId};
            if (bizCollBillBase.isShowExpenseLongName()) {
                editoptions.f7Json.displayFormat = "{displayName}";
            }
            editGridDom.wafGrid("setCellConfig", "expenseType", "editoptions", editoptions, rowid);
            // 分录行"项目"F7过滤实现按分录行费用支付公司隔离
            if (String(initData.Projectlsolate) == "true") {
                var projectOpts = editGridDom.wafGrid("getColumnConfig", "project",rowid).editoptions;
                projectOpts.subTagJson.filteritem = bizCollBillBase.getProjectF7Filteritem;
                editGridDom.wafGrid("setColumnConfig", "project", "editoptions", projectOpts,rowid);
            }else{
                var projectOpts = editGridDom.wafGrid("getColumnConfig", "project",rowid).editoptions;
                projectOpts.subTagJson.filteritem = waf.parseSql.getFilter("status","!=",2);
                editGridDom.wafGrid("setColumnConfig", "project", "editoptions", projectOpts,rowid);
            }

            //分录费用承担公司、承担部门初始化
            bizCollBillBase.initCompanyAndCostCenter();
        }
	    ,
        getOperationTypeFilter: function() {
            var gridDom = waf("#entries"),
                    rowId = gridDom.wafGrid("getSelectedRow");
            company = gridDom.wafGrid("getCell", rowId, "company") || waf("#applierCompany").wafPromptBox("getValue"),
                    costCenter = gridDom.wafGrid("getCell", rowId, "costCenter"),
                    operationTypeFilter = waf.parseSql.getFilter("id","in","nodata"),
                    companyId = (company && company.id) || "";
            costCenterId = (costCenter && costCenter.id) || "";
            waf.doPost({
                    action: "onchange",
                    data: {source: "company", companyId: companyId, billType: "BizAccountOutBill", costCenterId: costCenterId},
            async: false,
                    success: function(data) {
                if (data) {
                    operationTypeFilter = waf.parseSql.getFilter("id","in",data.operationTypeFilterIds);
                }
            }
			});
            return operationTypeFilter;
        }
    	,
        editGridLoadAfter:function(data){
            // 预算信息初始化
            _private.initBgData();
            // 币别缓存初始化(放在initMultiInfo()前面,initMultiInfo中刷新复现金额用到币别缓存)
            _private.updateCurrencyMap();
            // 分录多币别/其他部门承担费用初始化
            _private.initMultiInfo();
            // 初始化分录-项目
            //_private.initEntryProject();

        }
    	,
        initEntryProject: function() {
            var initData = bizCollBillBase.getModelInitData(),
                    model= _self.getCurrentModel(),
                    companyId = model.company && model.company.id,
                    editGridDom = waf("#entries");
            if (String(initData.Projectlsolate) == "true") {
                projectOptions = editGridDom.wafGrid("getColumnConfig", "project").editoptions;
                projectOptions.subTagJson.filteritem = bizCollBillBase.getProjectF7Filteritem;
                editGridDom.wafGrid("setColumnConfig", "project", "editoptions", projectOptions);
            }else{
                var projectOpts = editGridDom.wafGrid("getColumnConfig", "project",rowid).editoptions;
                projectOpts.subTagJson.filteritem = waf.parseSql.getFilter("status","!=",2);
                editGridDom.wafGrid("setColumnConfig", "project", "editoptions", projectOpts,rowid);
            }
        }
    	,
        loanEntryLoadAfter: function(data) {
            waf("#loanCheckEntries").wafGrid("calcFooterData");
            // 刷新冲借款分录多币别合计
            bizCollBillBase.refreshCurrencyTotal("loanCheckEntries", "checkAmountOri",
                    "checkAmount", "loanCheckCurrencyTotalText", _self._localeStr.WRITE_OFFS, _public.multiCurrencyId);
        }
    	,
        reqEntryLoadAfter:function(data){
            waf("#reqCheckEntries").wafGrid("calcFooterData");
            // 刷新冲借款分录多币别合计
            bizCollBillBase.refreshCurrencyTotal("reqCheckEntries", "checkAmountOri",
                    "checkAmount", "reqCheckCurrencyTotalText", _self._localeStr.WRITE_OFFS, _public.multiCurrencyId);
        }
	    ,
        collectionGridLoadAfter:function(data){
            var model = _self.getCurrentModel(),
                    collectionGridDom = waf("#editGrid_collection");
            var currencyOptions = collectionGridDom.wafGrid("getColumnConfig", "currencyType").editoptions;
            currencyOptions.subTagJson.filteritem = _private.getCollectionCurrencyFilter;
            collectionGridDom.wafGrid("setColumnConfig", "currencyType", "editoptions", currencyOptions);
            bizCollBillBase.refreshCurrencyTotal("editGrid_collection", "amountOri",
                    "amount", "collectionCurrencyTotalText", _self._localeStr.AMOUNT_TO_PAY, _public.multiCurrencyId);
        }
	    ,
        showPayerF7: function() {
            var gridDom = waf("#editGrid_collection");
            var collectionEntryF7 = waf("#collectionEntryF7");
            collectionEntryF7.wafPromptStandard("option", "colModel", "");//清空colModel缓存
            var rowId = gridDom.wafGrid("getSelectedRow");
            var payerTypeValue = gridDom.wafGrid("getCell", rowId,"payerType");
            var payerType = payerTypeValue.value || payerTypeValue;

            var model = _self.getCurrentModel(),
                    companyId = model.applierCompany && model.applierCompany.id;
            var initdata = bizCollBillBase.getModelInitData();
            var usedStatusfilter = waf.parseSql.getFilter("usedStatus","=",1);
            var usingSatusfilter = waf.parseSql.getFilter("supplierCompany.usingStatus","=",0);
            var companyId = waf.parseSql.getFilter("companyOrgUnit.id","=",companyId);
            var customerfilter = waf.parseSql.getFilter("CustomerCompanyInfo.usingStatus","=",0);
            if (payerType == "20") {
                collectionEntryF7.wafPromptStandard("option", "defaultColumn", "name");
                //collectionEntryF7.wafPromptStandard("option", "defaultSearchItem", "name+number");
                collectionEntryF7.wafPromptStandard("option", "defaultSearchItem", "name+number+mnemonicCode");
                collectionEntryF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7SupplierCompanyQueryWithDefaultStandard");
                collectionEntryF7.wafPromptStandard("option", "filteritem", waf.parseSql.mergeFilter([usedStatusfilter,usingSatusfilter,companyId]," and"));
            } else if (payerType == "30") {
                collectionEntryF7.wafPromptStandard("option", "defaultColumn", "name");
                //collectionEntryF7.wafPromptStandard("option", "defaultSearchItem", "name+number");
                collectionEntryF7.wafPromptStandard("option", "defaultSearchItem", "name+number+mnemonicCode");
                collectionEntryF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7CustomerCompanyQueryWithDefaultStandard");
                collectionEntryF7.wafPromptStandard("option", "filteritem", waf.parseSql.mergeFilter([usedStatusfilter,customerfilter,companyId]," and"));
            } else {
                collectionEntryF7.wafPromptStandard("option", "defaultColumn", "payee");
                collectionEntryF7.wafPromptStandard("option", "defaultSearchItem", "payee+bankAccount+BebankStr");
                //var collecionEntryFilter = " state = 1 and applier.id = '" + initdata.personId + "'";
                collectionEntryF7.wafPromptStandard("option", "filteritem", _public.getCollectionEntryFilter);
                collectionEntryF7.wafPromptStandard("option", "query", "com.kingdee.eas.cp.bc.app.CollectionAccountQuery");
            }
            waf("#collectionEntryF7").wafPromptStandard("open");
        }
    	,
        collectionEntryF7Change:function(event,value){
            var collectionEntryDom = waf("#editGrid_collection"),
                    currentRowId = collectionEntryDom.dataGrid("getSelectedRow"),
                    payerNameFlag = false,
                    selectValue = value.current;
            if (!selectValue) return;
            if (!selectValue.length ){
                selectValue = new Array(selectValue);
            }
            if (selectValue.length == 0) return;
            var rowObj = collectionEntryDom.wafGrid("getRowRealData", currentRowId);
            for (var i = 0, il = selectValue.length; i < il; i++) {
                var value = selectValue[i];
                var payerType = rowObj.payerType.value || rowObj.payerType;
                value.payerType = payerType;
                if (payerType == "20" || payerType == "30") {
                    value.payerName = value && value.name[waf.getContext().locale];
                    value.bebankStr = "";
                    value.bankAccount = "";
                    value.payerNumber = value && value.number;
                    waf.doPost({
                            url: waf.getContextPath() + "/cp/bc/bill.do?method=bizOutPayerNameChange",
                            data: {payerType: payerType, payerId: value.id},
                    async:false,
                            success: function(data) {
                        if (data) {
                            value.bebankStr = data.payerBank;
                            value.bankAccount = data.bankAccount;
                            value.payerNumber = data.payerNumber;
                        }
                    }
					});
                }else{
                    //只有在供应商为其他时，收款信息对应的银行状态必须为启用才可以选择
                    if(value.bebank && value.bebank.state && value.bebank.state.value == 2){
                        _self.showWarning(_self._localeStr.BEBANKSTATENOTCLOSE);
                        payerNameFlag = true;
                        break;
                    }
                    value.payerName = value.payee;
                    value.payerNumber = value.applier && value.applier.number;
                }
                var rowData = _private.getCollectionRowData(value);

                if (selectValue.length == 1) {//选择一行收款人信息,保留原有的收款金额
                    rowData.id = currentRowId;//保留收款分录id
                    collectionEntryDom.wafGrid("setRowData", currentRowId, rowData);
                }else{//选择多行收款信息
                    rowData.amountOri = 0.00;
                    rowData.amount = 0.00;
                    collectionEntryDom.wafGrid("addRow", {data: rowData, position: "after", src: currentRowId});
                }
            }
            //返回false保证下次选同一条记录还能进入change方法
            if(payerNameFlag)return false;
            if (selectValue.length > 1) {//选择多行时，要删除当前行
                collectionEntryDom.wafGrid("delRow", currentRowId);
            }
            collectionEntryDom.wafGrid("calcFooterData");
            bizCollBillBase.refreshCurrencyTotal("editGrid_collection", "amountOri",
                    "amount", "collectionCurrencyTotalText", _self._localeStr.AMOUNT_TO_PAY, _public.multiCurrencyId);
            waf("#collectionEntryF7").wafPromptBox("setValue", null);
        }
	    ,
        getCollectionRowData: function(value) {
            var collectionEntryDom = waf("#editGrid_collection"),
                    currentRowId = collectionEntryDom.dataGrid("getSelectedRow"),
                    rowObj = collectionEntryDom.wafGrid("getRowRealData", currentRowId);
            var model = _self.getCurrentModel();
            var	currencyType = model.currencyType,
                    exchangeRate = 1.0000,
                    convertMode = 0,
                    precision = 4;
            if (rowObj.currencyType) {//收款分录币别存在，取收款分录币别和汇率
                currencyType = rowObj.currencyType;
                exchangeRate = rowObj.exchangeRate;
                convertMode = rowObj.convertMode;
                precision = rowObj.exchangeRatePrecision;
            }
            //将收款人F7选中收款信息添加到收款分录，直接处理并返回value，
            //若收款信息实体和收款分录实体二开字段名保持一致，可以带出二开字段
            value.payerId = value.id,
                    value.payerType = value.payerType,
                    value.payerNumber = value.payerNumber,
                    value.payerName = value.payerName,
                    value.payerBank = value.bebankStr,
                    value.payerBankF7 =value.bebank,
                    value.payerAccount = value.bankAccount,
                    value.payMode = rowObj.payMode || model.payMode,
                    value.currencyType = currencyType,
                    value.exchangeRate = exchangeRate,
                    value.convertMode = convertMode,
                    value.exchangeRatePrecision = precision;
            //缓存收款人对象，这里要用cloneObject复制，否则value.payerNameEntryF7里面会还有payerNameEntryF7，刷新model会因循环赋值报错
            value.payerNameEntryF7 = waf.cloneObject(value);
            delete value.id; //删除收款信息的id,否则会将该id作为收款分录id保存到数据库
			/*var rowData = {
					payerId: value.id,
					payerType: value.payerType,
					payerNumber: value.payerNumber,
					payerName: value.payerName,
					payerNameEntryF7: value, //缓存收款人对象
					payerBank: value.bebankStr,
					payerBankF7:value.bebank,
					openArea: value.openArea,
					payerAccount: value.bankAccount,
					payMode: rowObj.payMode || model.payMode,
					currencyType: currencyType,
					exchangeRate: exchangeRate,
					convertMode: convertMode,
					exchangeRatePrecision: precision
				};*/
            //提供给二次开发扩展,给扩展字段设值
            //rowData = bizCollBillBase.getCollectionRowData_Ext(rowData, value, _public.collectionEntryProps);
            return value;
        }
	    ,
        showPayerBankF7: function() {
            waf("#entryPayerBankF7").wafPromptStandard("open");
        }
	    ,
        showPayerAccountF7: function() {
            waf("#entryPayerAccountF7").wafPromptStandard("open");
        }
	    ,
        entryPayerBankF7Change:function(event,value){
            var collectionEntryDom = waf("#editGrid_collection"),
                    currentRowId = collectionEntryDom.dataGrid("getSelectedRow"),
                    selectValue = value.current;
            if (!selectValue || selectValue.length == 0) return;
            var payerBank = selectValue.name.l2 ? selectValue.name.l2 : selectValue.name;
            collectionEntryDom.wafGrid("editStop");
            collectionEntryDom.wafGrid("setCell", currentRowId, "payerBank", payerBank);
            collectionEntryDom.wafGrid("setCell", currentRowId, "payerBankF7", selectValue);
            waf("#entryPayerBankF7").wafPromptBox("setValue",null,false);
        }
	    ,
        entryPayerAccountChange:function(event,value){
            var collectionEntryDom = waf("#editGrid_collection"),
                    currentRowId = collectionEntryDom.dataGrid("getSelectedRow"),
                    selectValue = value.current;
            if (!selectValue || selectValue.length == 0) return;
            //外部或内部供应商及客户取不同银行账户
            var payerAccount = selectValue.bankAccount || selectValue.bankAccountNumber;
            var bank = selectValue.bank ? selectValue.bank : "";
            collectionEntryDom.wafGrid("editStop");
            collectionEntryDom.wafGrid("setCell", currentRowId, "payerAccount", payerAccount);
            if (selectValue && selectValue.name) {
                collectionEntryDom.wafGrid("setCell", currentRowId, "payerBank", selectValue.bank && selectValue.bank.name[waf.getContext().locale]);
            } else if (selectValue && selectValue.bankAccount) {
                collectionEntryDom.wafGrid("setCell", currentRowId, "payerBank", selectValue.bank);
            }
            waf("#entryPayerAccountF7").wafPromptBox("setValue", null);//需要清空该f7值，不然选择相同的值不会触发值改变
        }
	    ,
        getCollectionCurrencyFilter: function() {
            // 初始化多收款人分录-币别过滤
            var currencyIDs = _public.currencyMap.keys(),
                    model = _self.getCurrentModel();
            if (model.currencyType) {
                currencyIDs.push(model.currencyType.id);
            }
            if(currencyIDs){
                return waf.parseSql.getFilter("id","in",currencyIDs);
            }else{
                return waf.parseSql.getFilter("id","in","nodata");
            }
        }
    	,
        submitBefore:function(e){
            var initData = bizCollBillBase.getModelInitData();
//            if(String(initData.CP049) == "true" && waf("#editGrid_collection").wafGrid("getDataIDs").length==0){
//            	_self.showError(_self._localeStr.COLLECTIONENTRY_IS_NOT_NULL);
//            	return false;
//            }
            //多收款人分录中收款人必输校验
            var editoptions = waf("#editGrid_collection").wafGrid("getColumnProp", "payerName", "editoptions"),
                    encashedAmount = waf("#encashedAmount").wafNumberField("getValue");
            if(!editoptions || !editoptions.validateJson || !editoptions.validateJson.rules) return;
            if(encashedAmount == 0.00){
                editoptions.validateJson.rules = "required:false";
                waf("#editGrid_collection").wafGrid("setColumnProp", "payerName", "editoptions", editoptions);
            }else{
                editoptions.validateJson.rules = "required:true";
                waf("#editGrid_collection").wafGrid("setColumnProp", "payerName", "editoptions", editoptions);
            }

        }
    	,
        pageOnLoadAfter:function(e){
            // 页面载入完成后刷新付现金额
            _public.refreshEncashedAmount();
            _public.isLoaded = true;
            //隐藏收款信息
            var initData = bizCollBillBase.getModelInitData();
            if(String(initData.CP049) == "false"){
                waf("#section_collectionAccount").wafSection("option", "hidden", true);
            }

            bizCollBillBase.insertRow("entries",_public.entryInitData);

            $("#gbox_loanCheckEntries_insertEntryRow").unbind("click").bind("click",function(){
                _private.loanGridAddRow("insert");
            });
            $("#gbox_reqCheckEntries_insertEntryRow").unbind("click").bind("click",function(){
                _private.reqGridAddRow("insert");
            });
            $("#gbox_editGrid_collection_insertEntryRow").unbind("click").bind("click",function(){
                _public.collectionGridAddRow("insert");
            });
            //初始化分录承担公司部门效验
            bizCollBillBase.initEntryValidate();
            //禁用字段置灰
            _private.setTableHeadLabel();

            _public.setWidgetCSS();

        },
        setTableHeadLabel:function(){
            waf("#editGrid_collection").wafGrid("setLabel","amount","",{"color":"#999"});
        }
    	,
        judgeDataModifyActionHandler: function() {
            if (!_self.isJudgeDataModify()) return false;
            var oldModel = _self.getOldModel();
            if (oldModel!=null){
                _self.fireEvent("fieldOnStoreEvent");
                var newModel = _self.getCurrentModel(),
                        newEntries = newModel.entries;
                for (var i = 0, il = newEntries.length; i < il; i++) {
                    newEntries[i].bgText = "";
                    newEntries[i].accountStandard = "";
                }
                if (waf.toJSONString(newModel)!=oldModel)
                    return true;
                else
                    return false;
            }
            return false;
        }
    	,
        setOldModelActionHandler: function() {
            setTimeout(function() {
                waf.dataBinder.storeField(_self.getCurrentModel());
                var model = _self.getCurrentModel(),
                        entries = model.entries;
                for (var i = 0, il = entries.length; i < il; i++) {
                    entries[i].bgText = "";
                    entries[i].accountStandard = "";
                }
                _self.setOldModel(waf.toJSONString(model));
            }, 10);
        }
    	,

        selectPayer: function() {
            var payerType = waf("#payerType").wafSelect("getValue");
            if ("20" == payerType || "30" == payerType) {
                // 供应商
                waf("#payerNameF7").wafPromptStandard("open");
            } else if ("10" == payerType) {
                // 选择收款人信息
                var winStyle={
                        OpenType: "iframe",
                        method: "POST",
                        data: {billType: "BizAccountOutBill"},
                modal: true,
                        openFromParent: false,
                        position: "center",
                        showTitleBar : true,
                        autoMax : false,
                        width: 800,
                        height: 570,
                        minHeight: 200,
                        minWidth: 420,
                        iconCls: "ui-icon-winIcon",
                        draggable: true,
                        resizable: false,
                        hideEffect: "slide",
                        retValCallBack: _private.doSelectPayer
    			};
                winStyle.title = _self._localeStr.PAYEE_INFORMATION;
                var uipk = "com.kingdee.eas.cp.bc.CollectionAccount_InvokeList";
                winStyle.url = waf.getDynamicListContextPath() + "?event=initialize&method=initalize&uipk=" + uipk;
                waf.window.open(winStyle);
            }
        }
    	,
        selectPayerBank:function(e){
            waf("#payerBankF7").wafPromptStandard("open");
        }
    	,
        payerBankChange:function(event,ui){
            if (!_public.needChangeEvent) return;
            _public.needChangeEvent = false;
            waf("#payerBankStr").wafPromptBox("setValue", null);
            _public.needChangeEvent = true;
        }
    	,
        payerBankF7Change:function(event,value){
            if (!_public.needChangeEvent) return;
            var newValue = value.current;
            _public.needChangeEvent = false;
            waf("#payerBank").wafText("setValue", newValue && newValue.name[waf.getContext().locale]);
            _public.needChangeEvent = true;
        }
    	,
        payerTypeChange:function(event,ui){
            waf("#payerName").wafText("setValue", null);
            waf("#payerNameF7").wafPromptBox("setValue", null);
            waf("#payerBank").wafText("setValue", null);
            waf("#payerBankStr").wafPromptBox("setValue", null);
            waf("#payerAccount").wafText("setValue", null);
            waf("#payerAccountF7").wafPromptBox("setValue", null);
            var model = _self.getCurrentModel();
            var newValue = ui.current,
                    payerF7Dom = waf("#payerNameF7"),
                    selectPayerAccountBtn = waf("#buttonSelectPayerAccount"),
                    companyId = model.applierCompany && model.applierCompany.id;
            switch (newValue) {
                case "10":	// 其他
                    waf("#buttonSelectApplier").wafLinkButton("option", "hidden", false);
                    waf(".payerNameF7").hide();
                    waf(".payerName").show();

                    selectPayerAccountBtn.wafLinkButton("option", "hidden", true);
                    break;
                case "20":	// 供应商
                    waf("#buttonSelectApplier").wafLinkButton("option", "hidden", true);
                    waf(".payerNameF7").show();
                    waf(".payerName").hide();

                    selectPayerAccountBtn.wafLinkButton("option", "hidden", false);
                    payerF7Dom.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7SupplierCompanyQueryWithDefaultStandard");
                    payerF7Dom.wafPromptStandard("option", "filteritem", " usedStatus = 1 and supplierCompany.usingStatus = 0 and companyOrgUnit.id = '" + companyId + "' ");
                    break;
                case "30":	// 客户
                    waf("#buttonSelectApplier").wafLinkButton("option", "hidden", true);
                    waf(".payerNameF7").show();
                    waf(".payerName").hide();

                    selectPayerAccountBtn.wafLinkButton("option", "hidden", false);
                    payerF7Dom.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7CustomerCompanyQueryWithDefaultStandard");
                    payerF7Dom.wafPromptStandard("option", "filteritem", " usedStatus = 1 and CustomerCompanyInfo.usingStatus = 0 and companyOrgUnit.id = '" + companyId + "' ");
                    break;
                default:
                    break;
            }
        }
    	,
        payerNameF7Change:function(event,value){
            // 设置收款银行/收款账号的值
            if (!_public.needChangeEvent) return;
            var newValue = value.current,
                    oldPayerBank = waf("#payerBank").wafText("getValue");
            waf("#payerId").wafText("setValue", newValue && newValue.id);
            // 出纳付款单处理如果是银行账户，显示开户公司的名称,这里不涉及到银行账户,不处理
            waf("#payerName").wafText("setValue", newValue && newValue.name[waf.getContext().locale]);

            var payerNameF7Id = newValue && newValue.id,
                    payerType = waf("#payerType").wafSelect("getValue");
            waf("#payerName").wafText("setValue", newValue && newValue.name[waf.getContext().locale]);
            waf.doPost({
                    url: waf.getContextPath() + "/cp/bc/bill.do?method=bizOutPayerNameChange",
                    data: {payerType: payerType, payerId: payerNameF7Id},
            success: function(data) {
                if (data) {
                    waf("#payerBank").wafText("setValue", data.payerBank);
                    waf("#payerAccount").wafText("setValue", data.bankAccount);
                }
            }
			});
        }
    	,
        selectPayerAccount:function(e){
            var payerType = waf("#payerType").wafSelect("getValue"),
                    payerId = waf("#payerId").wafText("getValue"),
                    payerAccountF7 = waf("#payerAccountF7");
            waf.doPost({
                    url: waf.getContextPath() + "/cp/bc/bill.do?method=initAccountF7",
                    data: {payerType: payerType, payerId: payerId},
            async: false,
                    success: function(result) {
                if (result) {
                    payerAccountF7.wafPromptStandard("option", "query", result.payerAccountQuery);
                    payerAccountF7.wafPromptStandard("option", "filteritem", result.payerAccountFilter);
                }
            }
			});

            payerAccountF7.wafPromptStandard("open");
        }
    	,
        payerAccountF7Change:function(event,value){
            var newValue = value.current,
                    payerAccountDom = waf("#payerAccount"),
                    payerBankDom = waf("#payerBank");
            if (newValue && newValue.name) {
                payerAccountDom.wafText("setValue", newValue.bankAccountNumber);
                payerBankDom.wafText("setValue", newValue.bank && newValue.bank.name[waf.getContext().locale])
            } else if (newValue && newValue.bankAccount) {
                payerAccountDom.wafText("setValue", newValue && newValue.bankAccount);
                payerBankDom.wafText("setValue", newValue.bank);
            }
        }
    	,
        showPayerInfoCheck: function() {
            var model = _self.getCurrentModel();
            if (model.entries && model.entries.length > 0) {
                for (var i = 0; i < model.collectionEntries.length; i++) {
                    var collectionEntry = model.collectionEntries[i];
                    if(collectionEntry.payerType && collectionEntry.payerType.value != 10){
                        waf("#savePayerInfoCheck").wafCheckbox("option", "hidden", true);
                        break;
                    }
                }
            }
        }
    	,
        initPayerInfo: function() {
            var model = _self.getCurrentModel(),
                    payerType = model.payerType && model.payerType.value,
                    payerId = model.payerId,
                    payerName = model.payerName,
                    payerF7Dom = waf("#payerNameF7"),
                    companyId = model.applierCompany && model.applierCompany.id;
            if (payerType == "10") {
                waf("#buttonSelectApplier").wafLinkButton("option", "hidden", false);
                waf(".payerNameF7").hide();
                waf(".payerName").show();
            } else {
                waf("#buttonSelectApplier").wafLinkButton("option", "hidden", true);
                waf(".payerNameF7").show();
                waf(".payerName").hide();
                var payer = {id: payerId, name: payerName};
                _public.needChangeEvent = false;
                waf("#payerNameF7").wafPromptBox("setValue", payer);
                _public.needChangeEvent = true;
            }
            if (payerType == "20") {
                payerF7Dom.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7SupplierCompanyQueryWithDefaultStandard");
                payerF7Dom.wafPromptStandard("option", "filteritem", " usedStatus = 1 and supplierCompany.usingStatus = 0 and companyOrgUnit.id = '" + companyId + "' ");
            } else if (payerType == "30") {
                payerF7Dom.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7CustomerCompanyQueryWithDefaultStandard");
                payerF7Dom.wafPromptStandard("option", "filteritem", " usedStatus = 1 and CustomerCompanyInfo.usingStatus = 0 and companyOrgUnit.id = '" + companyId + "' ");
            }
        }
    	,
        enableTaxChecked:function(event,ui){
            _private.initEntryTaxChecked(ui.checked);
        },
        initEntryTaxChecked: function(ifTaxChecked) {
            var editGridDom = waf("#entries");
            var isMultiCurrency = waf("#cbMultiCurrency").wafCheckbox("option", "checked");
            var isWithTax = waf("#isWithTax").wafCheckbox("isChecked");
            if (ifTaxChecked && isWithTax) {
                editGridDom.lockColumn("amountOriWithoutTax");
                editGridDom.wafGrid("setLabel","amountOriWithoutTax","",{"color":"#999"});
                editGridDom.unLockColumn("amountOri");
                editGridDom.wafGrid("setLabel","amountOri","",{"color":"#000"});
            } else if (ifTaxChecked && !isWithTax) {
                editGridDom.unLockColumn("amountOriWithoutTax");
                editGridDom.wafGrid("setLabel","amountOriWithoutTax","",{"color":"#000"});
                editGridDom.lockColumn("amountOri");
                editGridDom.wafGrid("setLabel","amountOri","",{"color":"#999"});
            } else if (!ifTaxChecked) {
                editGridDom.unLockColumn("amountOri");
                editGridDom.wafGrid("setLabel","amountOri","",{"color":"#000"});
            }
        }
    	,
        selectInvoice:function(e){
            var model = _self.getCurrentModel();
            waf.window.open({
                    //url地址的变动
                    url:waf.getDynamicListContextPath() + "?event=initialize&method=initalize&uipk=com.kingdee.eas.cp.bc.ElectronicInvoiceSelectList&waf2skin=easbase"+
                    "&companyId="+waf.encodeURIComponent(model.applierCompany.id)+
                    "&companyName="+model.applierCompany.name.l2+"&userId="+ waf.encodeURIComponent(model.applier.id)+"&modelId="+ waf.encodeURIComponent(model.id),
                    openType:"iframe",
                    method:'POST',
                    data:{},
            modal:true,
                    title: _self._localeStr.SELECT_THE_ELECTRONIC_INVOICE,
                    position: ['center'],
            width: 800,
                    height: 600,
                    minHeight:600,
                    minWidth:800,
                    draggable: true,
                    resizable: true,
                    retValCallBack: _self.setTable,
        });
        }
    	,
        initIsWithTaxDefault:function(){
            // 含税勾选框相关初始化
            var initData = bizCollBillBase.getModelInitData(),
                    isWithTaxCheckDom = waf("#isWithTax");
            var isWithTaxDefault = true;
            if(String(initData.CP060)=="false"){
                isWithTaxDefault=false;
            }
            isWithTaxCheckDom.wafCheckbox("option", "onchange", null);
            //设置含税勾选框是否默认勾选
            isWithTaxCheckDom.wafCheckbox("setChecked", isWithTaxDefault);
            _private.initIsWithTax(isWithTaxDefault);//设置含税不含税是否可编辑
            isWithTaxCheckDom.wafCheckbox("option", "onchange", _public.isWithTaxChange);
        }
    	,
        initIsWithTax:function(ifWithTaxChecked) {
            var gridDom = waf("#entries");
            var isEnableTax = waf("#isWithTax").wafCheckbox("option", "checked");
            if (isEnableTax) {
                gridDom.lockColumn("amountOriWithoutTax");
                gridDom.wafGrid("setLabel","amountOriWithoutTax","",{"color":"#999"});
                gridDom.unLockColumn("amountOri");
                gridDom.wafGrid("setLabel","amountOri","",{"color":"#000"});
            } else if (!isEnableTax) {
                gridDom.unLockColumn("amountOriWithoutTax");
                gridDom.wafGrid("setLabel","amountOriWithoutTax","",{"color":"#000"});
                gridDom.lockColumn("amountOri");
                gridDom.wafGrid("setLabel","amountOri","",{"color":"#999"});
            }
        }
    	,
        columnCostCenterChange:function(event,value){
            // 费用支付部门值改变事件
            // 清空当前行费用类型/业务累呗
            if(!_public.needChangeEvent) return;
            var gridDom = waf("#entries");
            var operationTypeId = null;
            var rowid = gridDom.wafGrid("getSelectedRow");
            gridDom.wafGrid("setCell", rowid, "costCenter",value.current);
            var operationType = gridDom.wafGrid("getCell", rowid, "operationType")
            if(operationType != ""){
                operationTypeId = operationType.id;
            }
            _private.entryCostCenterChange(rowid, value.current, operationTypeId);
            // 触发预算查询
            _private.showBgData("costCenter", value.current, false, rowid, true);
            //改变超预算说明
            _private.changeBudgetDescription(false);
        }
	    ,
        columnCompanyChange:function(event,value){
            if(!_public.needChangeEvent) return;
            var gridDom = waf("#entries");
            var rowid = gridDom.wafGrid("getSelectedRow");
            gridDom.wafGrid("setCell", rowid, "company",value.current);
            //置空项目，实现项目与公司隔离
            gridDom.wafGrid("setCell", rowid, "project", null);
            //_private.entryCompanyChange(rowid, value);
            if (_public.isNeedClearCostCenter) {
                gridDom.wafGrid("setCell", rowid, "costCenter", null);
                _public.needChangeEvent = false;
                waf("#entryCostCenter").wafPromptBox("setValue", null);
                _public.needChangeEvent = true;
            }
            var operationTypeId = null;
            var rowId = gridDom.wafGrid("getSelectedRow");
            var operationType = gridDom.wafGrid("getCell", rowId, "operationType")
            if(operationType != ""){
                operationTypeId = operationType.id;
            }
            bizCollBillBase.isNeedClearOperationTypeByChangeCompany(rowid, value.current, operationTypeId);
            // 触发预算查询
            _private.showBgData("company", value.current, false, rowid, true);
            //改变超预算说明
            _private.changeBudgetDescription(false);
        }
	    ,
        columnCurrencyTypeChange:function(event,value){
            if(!_public.needChangeEvent) return;
            var gridDom = waf("#entries");
            var rowid = gridDom.wafGrid("getSelectedRow");
            gridDom.wafGrid("setCell", rowid, "currencyType",value.current);
            _private.entryCurrencyTypeChange(rowid, value.current);
            //本位币 汇率不允许修改
            bizCollBillBase.initExchangeRateCss(value.current);
        }
	    ,
        columnExchangeRateChange:function(event,value){
            if(!_public.needChangeEvent) return;
            //更新币别缓存
            var gridDom = waf("#entries");
            var rowid = gridDom.wafGrid("getSelectedRow");
            gridDom.wafGrid("setCell", rowid, "exchangeRate",value.current);

            var rowObject = gridDom.wafGrid("getRowRealData", rowid);
            var currencyrateValue = bizCollBillBase.currencyrateFormatter(null,null,rowObject,null,null);
            //设置币别/汇率字段
            gridDom.wafGrid("setCell", rowid, "currencyrate", currencyrateValue);

            _private.updateCurrencyMap();
            _private.entryExchangeRateChange(rowid, value.current);
        }
	    ,
        companyCostFormatter:function(cellvalue, options, rowObject, act, iRow){
            return bizCollBillBase.companyCostFormatter(cellvalue, options, rowObject, act, iRow);
        }
	    ,
        currencyrateFormatter:function(cellvalue, options, rowObject, act, iRow){
            return bizCollBillBase.currencyrateFormatter(cellvalue, options, rowObject, act, iRow);
        }
    	,
        editGridCopyRow:function(e){
            var model = _self.getCurrentModel();
            var rowid = $(e.target).closest(".rowoverlay").attr("rowid");
            if(rowid!=null && rowid.length > 0){
                var rowObj = waf("#entries").wafGrid("getRowRealData", rowid);
                _public.entryInitData = {
                        accountStandard: rowObj.accountStandard,
                        bgText: rowObj.bgText,
                        happenTime: rowObj.happenTime,
                        company: rowObj.company,
                        costCenter: rowObj.costCenter,
                        currencyType: rowObj.currencyType,
                        convertMode: rowObj.convertMode,
                        exchangeRate: rowObj.exchangeRate,
                        exchangeRatePrecision: rowObj.exchangeRatePrecision,
                        expenseType: rowObj.expenseType,
                        operationType: rowObj.operationType,
                        purpose:rowObj.purpose,
                        invoiceNum:_self._localeStr.INVOICENUM,
                        amount: 0,
                        amountOri: 0,
                        budgetDo: 0,
                        amountOriWithoutTax: 0,
                        amountWithoutTax: 0
					};
            }else{
                _public.entryInitData = {
                        company: model.company,
                        costCenter: model.costedDept,
                        currencyType: model.currencyType,
                        exchangeRate: 1.0000,
                        convertMode: 0,
                        exchangeRatePrecision: 4,
                        amount: 0,
                        amountOri: 0,
                        budgetDo: 0,
                        amountOriWithoutTax: 0,
                        invoiceNum:_self._localeStr.INVOICENUM,
                        amountWithoutTax: 0
					};
            }
            // 分录表格新增行
//			var rowid = $(e.target).closest(".rowoverlay").attr("rowid");
            if (rowid == "") {
                waf("#entries").wafGrid("addRow", {data: _public.entryInitData});
            } else {
                waf("#entries").wafGrid("addRow", {src: rowid, data: _public.entryInitData,
                        position: "after"});
            }
            waf("#entries").wafGrid("setFloatActions", [{"remove": true, onclick: _private.editGridDelRow}]);
            // 隐藏冲销借款| 冲销申请 | 收款人分录的浮动工具条
            waf("#loanCheckEntries, #editGrid_collection, #reqCheckEntries").wafGrid("hideFloatBar");
        }
    	,
        editGridCellBefore:function(rowid,cellname,value,iRow,iCol,rowdata){
            return bizCollBillBase.editGridCellBefore(rowid,cellname,value,iRow,iCol,rowdata);
        }
    	,
        ePayerBankChange:function(event,ui){
            var collectionEntryDom = waf("#editGrid_collection"),
                    currentRowId = collectionEntryDom.dataGrid("getSelectedRow"),
                    selectValue = ui.current;

            collectionEntryDom.wafGrid("editStop");
            collectionEntryDom.wafGrid("setCell", currentRowId, "payerBankF7", null);

        }
        ,
        epayerNameChange:function(event,ui){
            var collectionEntryDom = waf("#editGrid_collection"),
                    currentRowId = collectionEntryDom.dataGrid("getSelectedRow");
            //收款人类型为其他时,银行账户值改变要清空收款人f7
            collectionEntryDom.wafGrid("setCell", currentRowId, "payerNameEntryF7", null);
            collectionEntryDom.wafGrid("setCell", currentRowId, "payerId", null);
        }
    	,
        costedDeptChange:function(event,value){
            if(value.current != null){
                waf.doPost({
                        action: "onchange",
                        data: {source: "costCenter", costCenterId: value.current.id, operationTypeId: null, billType: _self.getBillType()},
                showBlock: false,
                        success: function(result) {
                    _public.isCostCenterChange = true;
                    if(result){
                        waf("#company").wafPromptBox("setValue",result.company);
                    }
                    _public.isCostCenterChange = false;
                }
				});
            }
        }
    	,
        companyChange:function(event,value){
            var company = waf("#company").wafPromptBox("getValue");
            var supportedObj = waf("#supportedObj").wafPromptBox("getValue");
            if(company != null && supportedObj !=null  && supportedObj.company != null){
                if(company.id != supportedObj.company.id){
                    waf("#supportedObj").wafPromptBox("setValue",null);
                }
            }
            var costedDept = waf("#costedDept");
            var widgetName = costedDept.wafPromptBox("option", "subWidgetName");
            if(!_public.isCostCenterChange){
                costedDept.wafPromptBox("setValue",null);
            }
            var initData = bizCollBillBase.getModelInitData();
            if(bizCollBillBase.isTicketInvoice() && String(initData.CP03905)=="false"){
                //删除有发票的费用分录 兼容cp03905需求
                var rowDatas = waf("#entries").wafGrid("getAllRowData");
                for (var i = 0, il = rowDatas.length; i < il; i++) {
                    var ticketUrl = rowDatas[i].ticketUrl;
                    //如果ticketUrl有值，说明这个费用分录有发票相关联
                    if(ticketUrl){
                        waf("#entries").wafGrid("delRow", rowDatas[i].id);
                    }
                }
                //清空原本的发票明细分录
                _public.deleteTicketEntry();
            }
            //将界面的值刷新至model里面  兼容cp03905功能，切换完后，需要及时刷新model
            _self.fireEvent("fieldOnStoreEvent");
        }
    	,
        appendEntryRow:function(event,value){
            switch (value) {
                case "entries":
                    _private.entriesGridAddRow();
                    break;
                case "loanCheckEntries":
                    _private.loanGridAddRow("add");
                    break;
                case "editGrid_collection":
                    _private.collectionGridAddRow("add");
                    break;
                case "reqCheckEntries":
                    _private.reqGridAddRow("add");
                    break;
                default:
                    _public.appendEntryRow_EX(event,value);
                    break;
            }
        }
    	,
        deleteEntryRow:function(event,value){
            switch (value) {
                case "entries":
                    var rowId = waf("#entries").jqGrid("getGridParam", "selrow");
                    if (!rowId) {
                        rowId = waf("#entries").jqGrid("getRowId", waf("#entries").jqGrid("getPageRowCount"));
                    }
                    var rowObj = waf("#entries").wafGrid("getRowRealData", rowId);
                    _private.entryGridDelRow(rowId,rowObj);
                    break;
                case "loanCheckEntries":
                    _private.loanGridDelRow(event);
                    break;
                case "editGrid_collection":
                    _private.collectionGridDelRow(event);
                    break;
                case "reqCheckEntries":
                    _private.reqGridDelRow(event);
                    break;
                default:
                    _public.deleteEntryRow_EX(event,value);
                    break;
            }
        }
    	,
        copyEntryRow:function(e){
            // 分录表格新增行
            var entryDom = waf("#entries"),
                    rowid = entryDom.wafGrid("getSelectedRow");

            if (rowid == "" || rowid==undefined) {
                _self.showWarning(waf.wafEdit.info.selectRow);
                return;
            } else {
                var rowData = _public.entryAddCopyData(rowid);
                //如果与发票集成，必须要有分录id，分录id只能在后台获取
                if(bizCollBillBase.isTicketInvoice() &&
                        (!rowData.id || rowData.id.substring(0, 3) == 'jqg')){
                    waf.doPost({
                            action: "appendRow",
                            //必须同步，保证addrow创建的dom在设置控件属性之前
                            async:false,
                            data: {entryKey: "entries"},
                    success: function(result) {
                        if(result.id)rowData.id = result.id;
                        entryDom.wafGrid("addRow", {src: rowid, data: rowData, position: "after"});
                    }
		    		});
                }else{
                    entryDom.wafGrid("addRow", {src: rowid, data: rowData, position: "after"});
                }
            }
        }
    	,
        displayBgData: function(grid, rowid) {
            // 在分录表格上面显示预算信息
            if (!rowid) return;
            //显示报销额度
            var limitStr = waf(grid).wafGrid("getCell", rowid, "accountStandard") || "";
            waf("#accountLimitText").show();
            waf("#accountLimitText").wafText("setValue", limitStr);
            if(!bizCollBillBase.isShowBudgetBalance()) return;

            var bgDataStr = waf(grid).wafGrid("getCell", rowid, "bgText") || "";
            waf("#bgtextArea").show();
            waf("#bgtextArea").wafTextarea("setValue", bgDataStr);
        }
	    ,
        editGridSelectBefore:function(rowid,e){
            _private.displayBgData("#entries",rowid);
        }
    	,
        validateCompanyCost:function(vakue,dom){
            return bizCollBillBase.validateCompanyCost(vakue,dom);
        }
    	,
        initInvoice: function(){
            // cpbcinvoice
            // 初始化发票云相关
            if(bizCollBillBase.isTicketInvoice()){
                var bxdKey =_self.getCurrentModel().id;
                var initData = _self.getPageInitData();
                var pwyWS = websocket_eas.createWS({
                        name: bxdKey+_public.random,
                        env:initData.ticketEnv,
                        onMessage: function(msg, handerOk, handerFail){
                    //
                    console.log(msg);
                    var type = msg.type;
                    var data = msg.data;
                    if(type === 'saveEntrys'){
                        _private.changeInvoiceInfoAndEntry(data);
                        _private.invoiceClose();
                    }else if(type === 'saveEntrysOk'){
                        //关闭
                        _private.invoiceClose();
                    }else if(type === 'saveEntrysFail'){
                        handerOk({'errcode':'0000','data':'', 'description':'success'});
                    }else if(type === 'saveBill'){
                        _private.saveInvoice(data);
                        _private.invoiceClose();
                    }else if(type === 'saveBillOk'){
                        _private.invoiceClose();
                    }else if(type === 'saveBillFail'){
                        handerFail({'errcode':'2000','data':'0000', 'description':'fail'});
                    }
                }
	    		});
            }
        }
    	,
        saveInvoice:function(data,type,ok,fail){
            //加载最新的model到界面
            _self.fireEvent("fieldOnStoreEvent");
            //cpbcinvoice
            waf.doPost({
                    action: "saveInvoice",
                    data: {
                id:data.bxd_key,
                        number:data.billnumber,
                        model:waf.toJSONString(_self.getCurrentModel()),
                        userKey:_public.userKeyJson,
                        data:JSON.stringify(data.data)
            },
            async: false,
                    success: function(result) {
                console.log("saveInvoiceSucces");
                _public.ticketModel = result;
            },error:function(result){
                alert(result);
                console.log("saveInvoiceError");
            }
            });
        }
    	,
        changeInvoiceInfoAndEntry:function(data,type,ok,fail){
            //加载最新的model到界面
            _self.fireEvent("fieldOnStoreEvent");
            // cpbcinvoice
            waf.doPost({
                    action: "changeInvoice",
                    data: {
                id:data.bxd_key,
                        entryId:data.branch_id,
                        model:waf.toJSONString(_self.getCurrentModel()),
                        invoiceInfo:JSON.stringify(data.list)
            },
            async: false,
                    success: function(result) {
                //_private.notifyInvoiceSaveSuccess(data);
                console.log("changeInvoiceSuccess");
                result.entryId = data.branch_id;
                _public.ticketModel = result;
            },error:function(result){
                console.log("changeInvoiceError");
                waf.block.hide();
            }
            });
        }
    	,
        ticketlink:function(rowId, gridId){
            // showCloudInvoice
            //为true表示流程中心或者共享中心审批节点配编辑页面，只提供查看发票接口
            if (bizCollBillBase.isFromWFOrSSC()) {
                bizCollBillBase.showCloudInvoiceAndTicketLink(_public.random,rowId);
            }else{
                bizCollBillBase.showCloudInvoiceAndTicketLink(_public.random,rowId,true);
            }
        }
    	,
        selectCloudInvoice:function(e){
            // cpbcinvoice
            bizCollBillBase.selectCloudInvoice(_public.random);
        }
    	,
        booleanToInt:function(e){
            if(e){
                return 1;
            }else{
                return 0;
            }
        }
	    ,
        showCloudInvoice:function(e){
            bizCollBillBase.showCloudInvoiceAndTicketLink(_public.random);
        },
        invoiceClose:function(invoice){
            // cpbcinvoice
            waf.window.close();
            var result = _public.ticketModel;
            if(result.entryId){
                //修改分录后的刷新，兼容关联生成过来的单据
                _public.refreshChangeInvoice(result);
            }else{
                //正常刷新
                _public.refreshBillEntry(result);
                _public.refreshBillTicketEntry(result);
                _public.refreshBillAccountEntry(result);
            }
        }
	    ,
        ticketTaxRateFormatter:function(cellvalue, options, rowObject){
            if(cellvalue == 99){
                return _self._localeStr.MULTIPLE_TICKET_TAXRATE;
            }else{
                return cellvalue;
            }
        }
    	,
        ticketEntryloadComplete:function(data){
            //与发票集成，默认加载查验状态需要有颜色
            bizCollBillBase.ticketEntryloadComplete(data);
        }
    });//@endPrivate
        _self.setTable = function (datas){
            var rows = [],
            entryDom = waf("#entries"),
                    model = _self.getCurrentModel(),
                    rowIdstarts = entryDom.wafGrid("getDataIDs"),
                    cause = waf("#cause").wafTextarea("getValue"),
                    rowstartlength = rowIdstarts.length;
            //默认行删除处理
            if(rowstartlength ==1){
                var rowObj = entryDom.wafGrid("getRowRealData", rowIdstarts[0]),
                        tAmount = rowObj.amount,
                        tExpenseType = rowObj.expenseType,
                        tOperationType = rowObj.operationType;
                if (tAmount==0 && tExpenseType == "" && tOperationType == ""){
                    var rowId = entryDom.wafGrid("getRowId",1);
                    entryDom.wafGrid("delRow",rowId);
                }
            }
            for (var i = 0, il = datas.length; i < il; i++) {
                var tmpData = datas[i];
                var	data = {
                        company: model.company,
                        costCenter: model.costedDept,
                        currencyType: model.currencyType,
                        exchangeRate: 1.0000,
                        convertMode: 0,
                        exchangeRatePrecision: 4,
                        happenTime:tmpData.bizDate,
                        amount: 0,
                        amountOri: tmpData.amount,
                        budgetDo: 0,
                        purpose:tmpData.description,
                        ticketNumber:tmpData.number,
                        ticketUrl:tmpData.ticketurl,
                        ticketCode:tmpData.code
			};
                if (!cause){
                    cause = tmpData.description;
                }
                var rowIds = entryDom.wafGrid("getDataIDs"),
                        rowlength = rowIds.length,
                        //判断是否可以添加电子发票
                        addTicket = true;

                for(var j = 0; j < rowlength; j++){
                    var rowObj = entryDom.wafGrid("getRowRealData", rowIds[j]);
                    if(rowObj.ticketNumber==tmpData.number){
                        addTicket = false;
                    }
                }
                if(addTicket){
                    entryDom.wafGrid("addRow", {data: data});
                    var rowid = entryDom.wafGrid("getRowId",rowlength+1);
                    _public.entryAmountOriChange(rowid, data.amountOri);
                }

            }

            //计算总计金额
            entryDom.wafGrid("editStop");
            entryDom.wafGrid("calcFooterData");
            //刷新附件列表
            //waf("#webAttachment").webAttachment("showFiles");
            if (cause){
                waf("#cause").wafTextarea("setValue", cause);
            }
            _public.refreshEncashedAmount();
        };

        waf.extend(_public, {
                budgetmaybechange: false,
                isLoaded: false,
                cp037TimeOut:0,//cp037为是时，关闭页面窗口等待时间
                isLoanAccount:true,
                entryInitData: {},
        collectionEntryInitData: {},
        botpAdapter: "com.kingdee.eas.cp.bc.dynamic.utils.DailyBillBotpDestBosTypeFindAdapter",
                ifInitLoanGrid: false,	// 是否自动加载可用冲借款
                multiCurrencyId: "cbMultiCurrency",	// 多币别复选框ID
                needChangeEvent: true,	// 是否需要出发值改变事件标志位
                isNeedClearCostCenter: true,	// 是否需要清空费用承担部门
                isCostCenterChange: false,  //表头费用承担部门是否改变
                currencyMap: new Map(),	// 币别缓存
                reportCoverQueryPK: "com.kingdee.eas.cp.bc.app.BizAccountBillOutForR1PrintQuery",	// TODO 打印封面queryPK
                reportCoverTemplate: "/FI/CPBC/BizAccountOutBill/image",	// 打印封面模板路径
                collectionEntryProps: [],	// 收款信息分录选择收款人时,要额外填充的属性
        needOtherDeptChange: true,
                useMorePayer: true,	// 是否使用多收款人
                showLoanAccount: true,	// 是否显示备用金
                rowAddType:"add",
                // cpbcinvoice 以下
                taxpayerID:"",// 先固定，基础会在company里面维护。后继在后台取。
                eid:"0001",
                sign:"00d0d01",
                billId:"billId",
                billNumber:"billNumber",
                entryId:"entryId",
                timestamp:"1506043403513",
                random:Math.random(),
                url:"",
                tUsed:"",
                tEnterprise:"",
                tTaxNumber:"",
                tCheckFail:"",
                userKeyJson:"fticket",
                ticketModel:{},
        refreshBillEntry: function(result) {
            //清空原本的费用明细分录
            _public.deleteBizOutEntry();
            //将后台返回的新分录刷新至界面
            _public.refreshBizOutEntry(result);
            _public.refreshTotalAmount("entries", "amount", "amount", true);
            //将界面的值刷新至model里面  没有这个会造成有时候删掉的票又回来了
            _self.fireEvent("fieldOnStoreEvent");
        },
        refreshChangeInvoice: function(result) {
            //获得要修改的分录id，并刷新当前分录的信息
            _public._refreshBizOutEntryChange(result);
            //刷新发票分录
            _public.refreshBillTicketEntry(result);
            //刷新金额
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        },
        deleteBizOutEntry: function() {
            //清空所有没有原单id的费用明细分录，重新刷新
            var entrydom = waf("#entries"),
                    entries_rowids = entrydom.wafGrid("getDataIDs");
            for (var i = 0, il = entries_rowids.length; i < il; i++) {
                var sourceBillId = entrydom.wafGrid("getCell", entries_rowids[i], "sourceBillID");
                if(!sourceBillId){
                    entrydom.wafGrid("delRow", entries_rowids[i]);
                }
            }
        },
        refreshBizOutEntry: function(result) {
            var entrydom = waf("#entries");
            if(result && result.entries){
                for (var i = 0, il = result.entries.length; i < il; i++) {
                    var tmpData = result.entries[i];
                    var data = {
                            id:tmpData.id,
                            amount:tmpData.amount,
                            amountApproved:tmpData.amountApproved,
                            amountApprovedOri:tmpData.amountApprovedOri,
                            amountApprovedOriWithoutTax:tmpData.amountApprovedOriWithoutTax,
                            amountApprovedWithoutTax:tmpData.amountApprovedWithoutTax,
                            amountBalance:tmpData.amountBalance,
                            amountBalanceOri:tmpData.amountBalanceOri,
                            amountEncashed:tmpData.amountEncashed,
                            amountOri:tmpData.amountOri,
                            taxRate:tmpData.taxRate,
                            tax:tmpData.tax,
                            amountOriWithoutTax:tmpData.amountOriWithoutTax||tmpData.amountOri,
                            amountStriked:tmpData.amountStriked,
                            amountUsed:tmpData.amountUsed,
                            amountUsedOri:tmpData.amountUsedOri,
                            amountWithoutTax:tmpData.amountWithoutTax||tmpData.amount,
                            billingDate:tmpData.billingDate,
                            comment:tmpData.comment,
                            company:tmpData.company,
                            costCenter:tmpData.costCenter,
                            currencyType:tmpData.currencyType,
                            exchangeRate:tmpData.exchangeRate,
                            convertMode:tmpData.convertMode,
                            exchangeRatePrecision:tmpData.exchangeRatePrecision,
                            expenseType:tmpData.expenseType,
                            happenTime:tmpData.happenTime,
                            invoiceNum:tmpData.invoiceNum,
                            name:tmpData.name,
                            ticketUrl:tmpData.ticketUrl,
                            operationType:tmpData.operationType
					};
                    entrydom.wafGrid("addRow", {data: data, position: "last"});
                }
                entrydom.wafGrid("reloadGrid");
            }else{
                console.log("refreshBillEntry Error,result.entries is null");
            }
        },
        refreshBillTicketEntry: function(result) {
            //清空原本的费用明细分录
            _public.deleteTicketEntry();
            //将后台返回的新分录刷新至界面
            _public.refreshTicketEntry(result);
            //将界面的值刷新至model里面  没有这个会造成有时候删掉的票又回来了
            _self.fireEvent("fieldOnStoreEvent");
        },
        deleteTicketEntry: function() {
            //删除所有发票分录
            var ticketEntries = waf("#ticketEntries"),
                    rowIds = ticketEntries.wafGrid("getDataIDs");
            for (var i = 0, il = rowIds.length; i < il; i++) {
                ticketEntries.wafGrid("delRow", rowIds[i]);
            }
        },
        refreshTicketEntry: function(result) {
            var ticketEntries = waf("#ticketEntries");
            if(result && result.tickeEntries){
                for (var i = 0, il = result.tickeEntries.length; i < il; i++) {
                    var tmpData = result.tickeEntries[i];
                    var data = {
                            amout: tmpData.amout,
                            serviceName: tmpData.serviceName,
                            tax: tmpData.tax,
                            taxRate: tmpData.taxRate,
                            ticketCode: tmpData.ticketCode,
                            ticketNumber: tmpData.ticketNumber,
                            ticketType: tmpData.ticketType,
                            ticketTypeId:tmpData.ticketTypeId,
                            taxClassNumber: tmpData.taxClassNumber,
                            serialNo: tmpData.serialNo,
                            billingDate:  tmpData.billingDate,
                            isDeduction: tmpData.isDeduction,
                            deductionTax: tmpData.deductionTax,
                            checkStates: tmpData.checkStates,
                            bizEntryBill:tmpData.bizEntryBill
					};
                    waf("#ticketEntries").wafGrid("addRow", {data: data, position: "last"});
                }
                waf("#ticketEntries").wafGrid("reloadGrid");
                if(result.tickeEntries.length==0) {
                    waf("#section_ticket").wafSection("close");
                }else{
                    waf("#section_ticket").wafSection("open");
                }
            }else{
                console.log("refreshBillTicketEntry Error,result.ticketEntries is null");
            }
        },
        refreshBillAccountEntry: function(result) {
            //清空原本的费用明细分录
            _public.deleteAccountEntry();
            //将后台返回的新分录刷新至界面
            _public.refreshAccountEntry(result);
            //刷新付现金额
            _public.refreshTotalAmount("entries", "amount", "amount", true);
            //将界面的值刷新至model里面  没有这个会造成有时候删掉的票又回来了
            _self.fireEvent("fieldOnStoreEvent");
        },
        deleteAccountEntry: function() {
            //删除所有收款人分录
            var collEntries = waf("#editGrid_collection"),
                    collEntries_rowids = collEntries.wafGrid("getDataIDs");

            for (var i = 0, il = collEntries_rowids.length; i < il; i++) {
                collEntries.wafGrid("delRow", collEntries_rowids[i]);
            }
        },
        refreshAccountEntry: function(result) {
            var collEntries = waf("#editGrid_collection");
            //重新刷新
            for (var i = 0, il = result.collectionEntries.length; i < il; i++) {
                var tmpData = result.collectionEntries[i];
                var data = {
                        payerName:tmpData.payerName,
                        payerBank:tmpData.payerBank,
                        payerAccount:tmpData.payerAccount,
                        openArea: {name: tmpData.accountOpenArea},
                payerType:tmpData.payerType,
                        amount:tmpData.amount,
                        amountOri:tmpData.amountOri,
                        currencyType:tmpData.currencyType,
                        exchangeRate:tmpData.exchangeRate,
                        convertMode: tmpData.convertMode,
                        exchangeRatePrecision: tmpData.exchangeRatePrecision,
                        payMode: _self.getCurrentModel().payMode
				};
                collEntries.wafGrid("addRow", {data: data, position: "last"});
            }
            collEntries.wafGrid("reloadGrid");
        },
        delInvoiceClose: function(result) {
            if (result) {
                var ticketEntries = waf("#ticketEntries"),
                        rowIds = ticketEntries.wafGrid("getDataIDs");

                for (var i = 0, il = rowIds.length; i < il; i++) {
                    ticketEntries.wafGrid("delRow", rowIds[i]);
                }
                if(!result.tickeEntries)return;
                for (var i = 0, il = result.tickeEntries.length; i < il; i++) {
                    var tmpData = result.tickeEntries[i];
                    var data = {
                            amout: tmpData.amout,
                            serviceName: tmpData.serviceName,
                            tax: tmpData.tax,
                            taxRate: tmpData.taxRate,
                            ticketCode: tmpData.ticketCode,
                            ticketNumber: tmpData.ticketNumber,
                            taxClassNumber:tmpData.taxClassNumber,
                            bizEntryBill:tmpData.bizEntryBill,
                            serialNo: tmpData.serialNo,
                            isDeduction: tmpData.isDeduction,
                            deductionTax: tmpData.deductionTax,
                            ticketTypeId:tmpData.ticketTypeId,
                            currencyType: _self.getCurrentModel().currencyType,
                            ticketUrl:tmpData.ticketUrl,
                            ticketType: tmpData.ticketType
					};
                    waf("#ticketEntries").wafGrid("addRow", {data: data, position: "last"});
                }
                waf("#ticketEntries").wafGrid("reloadGrid");
                _self.fireEvent("fieldOnStoreEvent");
                if(result.tickeEntries.length==0) {
                    waf("#section_ticket").wafSection("close");
                }else{
                    waf("#section_ticket").wafSection("open");
                }
            }
        },
        _refreshBizOutEntryChange: function(result) {
            var entrydom = waf("#entries");
            if(result && result.entries){
                for (var i = 0, il = result.entries.length; i < il; i++) {
                    var tmpData = result.entries[i];
                    //获取要更新的分录
                    if(tmpData.id == result.entryId){
                        var data = {
                                amountOri:tmpData.amountOri,
                                amountOriWithoutTax:tmpData.amountOriWithoutTax||tmpData.amountOri,
                                invoiceNum:tmpData.invoiceNum,
                                tax:tmpData.tax,
                                amount:tmpData.amount,
                                amountWithoutTax:tmpData.amountWithoutTax||tmpData.amount,
                                taxRate:tmpData.taxRate,
                                ticketUrl:tmpData.ticketUrl
						};
                        entrydom.wafGrid("setRowData", result.entryId, data);
                    }
                }
                entrydom.wafGrid("reloadGrid");
            }else{
                console.log("refreshBillEntryChange Error,result.entries is null");
            }
        },
        showTicket:function(){
            var editGridDom = waf("#entries"),
                    showTickets = bizCollBillBase.isTicketInvoice(),
                    hiddenInvoice = true,
                    model = _self.getCurrentModel();
            if(model.state && (model.state.value <= 25 || model.state.value == 40)){
                hiddenInvoice = false;
            }
            waf("#selectInvoicebtn").wafLinkButton("option", "hidden", !showTickets || hiddenInvoice);
            waf("#lookInvoicebtn").wafLinkButton("option", "hidden", !showTickets);
            editGridDom.wafGrid(showTickets ? "showColumn":"hideColumn",["invoiceNum"]);
            if(!showTickets){
                waf("#section_ticket").hide();
            }else if(!model.tickeEntries||model.tickeEntries.length==0) {
                waf("#section_ticket").wafSection("close");
            }
            var ticketEntriesDom = waf("#ticketEntries"),
                    CP03904 = bizCollBillBase.ticketInspection();
            //查验不通过发票允许导入才显示查验状态和人工查验说明字段
            ticketEntriesDom.wafGrid(CP03904 ? "showColumn":"hideColumn",["checkStates","artificialPassExplain"]);
        },
        // cpbcinvoice 以上
        setInitComponentOpt: function(type, option, com) {
            var model = _self.getCurrentModel();
            var initData = bizCollBillBase.getModelInitData();
            switch (option.id) {
                case "applier":
                    // 申请人F7  getApplierFilter() 调用函数    getApplierFilter是字符串
                    option.subWidgetOptions.filteritem = _public.getApplierFilter;
                    if (String(initData.CP044) == "true") {
                        option.subWidgetOptions.isShowAllAdminBox = true;
                    } else {
                        option.subWidgetOptions.isShowAllAdminBox = false;
                    }
                    break;
                case "entryCompany":
                    if (String(initData.CP019) == "true") {
                        option.F7Type = "F7Standard",
                                option.subWidgetName = "wafPromptStandard",
                                option.subWidgetOptions = {
                                        title: _self._localeStr.ENTRY_COMPANY,
                                query: "com.kingdee.eas.cp.bc.app.CompanyOrgUnitQuery",
                                filteritem: bizCollBillBase.getEntryCompanyFilter
					};
                    } else {
                        option.subWidgetOptions.filteritem = bizCollBillBase.getEntryCompanyFilter;
                    }
                    break;
                case "entryCostCenter":
                    // 分录 - 费用承担部门
                    if (String(initData.CP019) == "true") {
                        option.F7Type = "F7Standard";
                        option.subWidgetName = "wafPromptStandard";
                        option.subWidgetOptions = {
                                title: _self._localeStr.COSTEDDEPT,
                                query: "com.kingdee.eas.cp.bc.app.CostCenterOrgUnitQuery4AsstAcct",
                                filteritem: bizCollBillBase.getEntryCostDeptFilter
					};
                    } else {
                        option.subWidgetOptions.filteritem = bizCollBillBase.getEntryCostDeptFilter;
                    }
                    break;
                case "company":
                    // 表头 - 费用承担公司
                    if (String(initData.CP019) == "true") {
                        option.F7Type = "F7Standard";
                        option.subWidgetName = "wafPromptStandard";
                        option.subWidgetOptions = {
                                query: "com.kingdee.eas.cp.bc.app.CompanyOrgUnitQuery",
                                filteritem: bizCollBillBase.getHeadCompanyFilter
					};
                    } else {
                        option.subWidgetOptions.filteritem = bizCollBillBase.getHeadCompanyFilter;
                    }
                    break;
                case "costedDept":
                    // 表头 - 费用承担部门
                    if (String(initData.CP019) == "true") {
                        option.F7Type = "F7Standard";
                        option.subWidgetName = "wafPromptStandard";
                        option.subWidgetOptions = {
                                query: "com.kingdee.eas.cp.bc.app.CostCenterOrgUnitQuery4AsstAcct",
                                filteritem: bizCollBillBase.getHeaderCostDeptFilter
					};
                    } else {
                        option.subWidgetOptions.filteritem = bizCollBillBase.getHeaderCostDeptFilter;
                    }
                    break;
                default:
                    break;
            }
            // 焦点顺序调整
            if (type == "linkButton" || type == "menuButton" || option.tagClass == "removeFocusTab") {
                option.tabindex = -1;
            }

            //二开扩展初始化页面控件接口
            bizCollBillBase.setInitComponentOpt_Ext(type, option, com);
        }
        ,
        getCollectionEntryFilter: function() {
            // 收款人F7过滤处理
            var model = _self.getCurrentModel(),
                    initData = bizCollBillBase.getModelInitData(),
                    personId = initData && initData.personId,
                    personList = initData && initData.applierList,
                    applier = waf("#applier").wafPromptBox("getValue"),
                    applierId = "";
            if (applier) applierId = applier.id;
            var user = waf.getContext().currentUser.id;
        	/*var collectionEntryFilter = " state = 1 and (applier.id = '" + personId +
	                 "' or creator.id = '" + user +
			         "' or applier.id in (select FReimbursePersonID from T_BC_ProxyReimburse " +
			         " where FProxyPersonID = '" + personId + "'  and fstate = 1) " +
			         " or applier.id in ('" + (Array.prototype.join.call(personList, "\',\'") || "nodata")+ "') )";*/
            var collectionEntryFilter = waf.parseSql.getFilter("parms","=","#FILTERSTART{Type:\\\"F7\\\",PromptBox:\\\"payer\\\",personId:\\\"" + initData.personId +"\\\",user:\\\""+ user +"\\\",personList:\\\"" + personList + "\\\",applierId:\\\"" + applierId + "\\\"}#FILTEREND");

            return collectionEntryFilter;
        },
        getApplierFilter: function() {
            var promptOrgUnit = waf("#orgUnit"),
                    orgUnit = promptOrgUnit.wafPromptBox("getValue"),
                    initData = bizCollBillBase.getModelInitData(),
                    applierFilter = " ",
                    model = _self.getCurrentModel(),
            adminOrgUnitIds = [],
            companyId = model.applierCompany && model.applierCompany.id;
            var applier = waf("#applier").wafPromptBox("getValue");
            if (orgUnit && orgUnit.id) {
                adminOrgUnitIds = [orgUnit.id];
            } else if (initData && initData.adminOrgUnitIds) {
                adminOrgUnitIds = initData.adminOrgUnitIds;
            }
            var isShowCompanyPerson = initData.CP044;
            if(isShowCompanyPerson == "false"){
            	/* applierFilter = " (deletedStatus != 2 and id = '" + initData.personId +"') " +
                 " or id in (select FReimbursePersonID from T_BC_ProxyReimburse " +
                 " where FProxyPersonID = '" + initData.personId + "' and FCompanyID = '"+ companyId +"' and fstate = 1) " +
                 " or adminOrgUnit.id in (select FCostCenterId from T_BC_ProxyReimburse where FCostCenterId " +
                 " in ('" + (Array.prototype.join.call(initData.adminOrgs, "\',\'") || "nodata")+ "') and FCompanyID = '"+ companyId +"')";*/
                return waf.parseSql.getFilter("parms","=","#FILTERSTART{Type:\\\"F7\\\",PromptBox:\\\"applier\\\",personId:\\\"" + initData.personId +"\\\",companyId:\\\""+ companyId +"\\\",costCenterId:\\\""
                        + initData.adminOrgs + "\\\"}#FILTEREND");
            }else{
            	/*applierFilter = " adminOrgUnit.id in ('" + Array.prototype.join.call(adminOrgUnitIds, "\',\'") + "') and " +
    			" deletedStatus != 2 ";*/
                var adminOrgUnitFilter= waf.parseSql.getFilter("adminOrgUnit.id","in",adminOrgUnitIds);
                var StatusFilter = waf.parseSql.getFilter("deletedStatus","!=","2");
                var inServiceFilter = waf.parseSql.getFilter("EmployeeType.inService","in",[1,4]);
                return waf.parseSql. mergeFilter([adminOrgUnitFilter, StatusFilter,inServiceFilter],"#1 and #2 and #3");
            }
            return applierFilter;
        },
        getOperationTypeId: function() {
            var entryDom = waf("#entries"),
                    currentRowId = entryDom.wafGrid("getSelectedRow"),
                    operationType = entryDom.wafGrid("getCell", currentRowId, "operationType");
            return operationType && operationType.id;
        }
    	,
        getCostCenterId: function() {
            var entryDom = waf("#entries"),
                    currentRowId = entryDom.wafGrid("getSelectedRow"),
                    costCenter = entryDom.wafGrid("getCell", currentRowId, "costCenter");
            return costCenter && costCenter.id;
        }
    	,
        getCompanyId: function() {
            var entryDom = waf("#entries"),
                    currentRowId = entryDom.wafGrid("getSelectedRow"),
                    company = entryDom.wafGrid("getCell", currentRowId, "company");
            if (!company) company = waf("#applierCompany").wafPromptBox("getValue");
            return company && company.id;
        }
    	,
        initEntryData: function() {
            var model = _self.getCurrentModel();
            _public.entryInitData = {
                    company: model.company,
                    costCenter: model.costedDept,
                    currencyType: model.currencyType,
                    exchangeRate: 1.0000,
                    convertMode: 0,
                    exchangeRatePrecision: 4,
                    amount: 0,
                    amountOri: 0,
                    budgetDo: 0,
                    amountWithoutTax: 0,
                    amountOriWithoutTax: 0,
                    tax:0,
                    taxRate:0,
                    invoiceNum: _self._localeStr.INVOICENUM
			};
            _public.collectionEntryInitData = {
                    amountOri: 0.00,
                    amount: 0.00,
                    currencyType: model.currencyType,
                    payerType:model.payerType,
                    payMode: model.payMode,
                    exchangeRate: 1.0000,
                    convertMode: 0,
                    exchangeRatePrecision: 4
			};
        }
	    ,
        entryAddCopyData:function(rowId){
            //添加分录复制选中行值
            var rowData = {};
            if(!rowId) return rowData;
            var rowObj = waf("#entries").wafGrid("getRowRealData", rowId);
            rowData = {
                    company: rowObj.company,
                    costCenter: rowObj.costCenter,
                    expenseType: rowObj.expenseType,
                    operationType: rowObj.operationType,
                    project : rowObj.project,
                    purpose : rowObj.purpose,
                    happenTime : rowObj.happenTime,
                    billingDate : rowObj.billingDate,
                    currencyType: rowObj.currencyType,
                    exchangeRate: rowObj.exchangeRate,
                    convertMode: rowObj.convertMode,
                    exchangeRatePrecision: rowObj.exchangeRatePrecision,
                    amount: 0,
                    amountOri: 0,
                    amountOriWithoutTax: 0,
                    amountWithoutTax: 0,
                    tax:0,
                    taxRate:0,
                    invoiceNum:_self._localeStr.INVOICENUM,
                    budgetDo: 0
				};
            return rowData;
        },
        getPositionFilter: function() {
            // 获取表头-职位过滤条件
            var applier = waf("#applier").wafPromptBox("getValue"),
                    applierId = (applier && applier.id) || "",
                    adminOrgUnit = waf("#orgUnit").wafPromptBox("getValue"),
                    adminOrgUnitId = (adminOrgUnit && adminOrgUnit.id) || "",
                    positionFilter = "";
            if (applierId){
                positionFilter = waf.parseSql.getFilter("Person.id ","=",applierId);
            }
            if (adminOrgUnitId) {
                if(positionFilter){
                    positionFilter =  waf.parseSql. mergeFilter([waf.parseSql.getFilter("adminOrgUnit.id","=",adminOrgUnitId), positionFilter],"#1 and #2");
                }else{
                    positionFilter = waf.parseSql.getFilter("adminOrgUnit.id","=",adminOrgUnitId);
                }
            }
            return positionFilter;
        },
        entryTaxRateChange: function(rowid) {
            var isWithTax = waf("#isWithTax").wafCheckbox("isChecked");
            var editGridDom = waf("#entries");
            var amountOriWithoutTax = editGridDom.wafGrid("getCell", rowid, "amountOriWithoutTax") || 0,
                    taxRate = editGridDom.wafGrid("getCell", rowid, "taxRate") || 0;
            var amountOri = editGridDom.wafGrid("getCell", rowid, "amountOri") || 0;
            if (isWithTax) {
                var amountOriWithoutTax = _self.setPrecision(amountOri /( 1 + taxRate / 100));
                var tax = _self.setPrecision(amountOriWithoutTax * taxRate / 100);
                editGridDom.wafGrid("setCell", rowid, "amountOriWithoutTax", Number(amountOriWithoutTax));
            } else {
                var tax = _self.setPrecision(amountOriWithoutTax * taxRate / 100);
                amountOri =  Number(amountOriWithoutTax) + Number(tax);
                editGridDom.wafGrid("setCell", rowid, "amountOri", _self.setPrecision(amountOri));
            }
            editGridDom.wafGrid("setCell", rowid, "tax", tax);
            // 更新本位币金额
            _public.refreshEntryAmount(rowid);
            // 刷新合计金额
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        },
        entryTaxChange: function (rowid, value) {
            var isWithTax = waf("#isWithTax").wafCheckbox("isChecked");
            var editGridDom = waf("#entries");
            var tax = editGridDom.wafGrid("getCell", rowid, "tax") || 0;
            var amountOriWithoutTax = editGridDom.wafGrid("getCell", rowid, "amountOriWithoutTax") || 0;
            var amountOri = editGridDom.wafGrid("getCell", rowid, "amountOri") || 0;
            if (isWithTax) {
                amountOriWithoutTax = Number(amountOri) - Number(tax);
                editGridDom.wafGrid("setCell", rowid, "amountOriWithoutTax", _self.setPrecision(amountOriWithoutTax));
            } else {
                amountOri = Number(amountOriWithoutTax) + Number(tax);
                editGridDom.wafGrid("setCell", rowid, "amountOri", _self.setPrecision(amountOri));
            }
            editGridDom.wafGrid("setCell", rowid, "tax", tax);
            var convertMode = editGridDom.wafGrid("getCell", rowid, "convertMode");
            var exchangeRate = editGridDom.wafGrid("getCell", rowid, "exchangeRate");
            var amountOri = editGridDom.wafGrid("getCell", rowid, "amountOri") || 0;
            var amount = _self.computeAmount(amountOri, convertMode, exchangeRate);
//			var amount = bizCollBillBase.ifConvertMode(convertMode) ?  _self.divideBigDecimal(amountOri,exchangeRate)
//					: _self.multiplyBigDecimal(amountOri,exchangeRate);
            editGridDom.wafGrid("setCell", rowid, "amount", _self.setPrecision(amount));
            // 刷新合计金额
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        }
		,
        entryAmountOriChange: function(rowid, value) {
            _public.refreshEntryAmount(rowid);
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        }
    	,
        refreshEntryAmountOri: function(rowid, value) {
            // 刷新分录-报销金额原币
            var editGridDom = waf("#entries"),
                    taxRate = editGridDom.wafGrid("getCell", rowid, "taxRate") || 0,
                    amountWithoutTax = editGridDom.wafGrid("getCell", rowid, "amountWithoutTax") || 0,
                    tax = _self.setPrecision(amountWithoutTax * taxRate / 100);
            editGridDom.wafGrid("setCell", rowid, "tax", tax);
            var amountOri = _self.setPrecision(Number(amountWithoutTax) + Number(tax));
            editGridDom.wafGrid("setCell", rowid, "amountOri", amountOri);
        }
    	,
        refreshEntryTaxAmount: function(rowid) {
            // 刷新指定行本位币不含税金额
            var editGridDom = waf("#entries"),
                    convertMode = editGridDom.wafGrid("getCell", rowid, "convertMode") || 0,
                    exchangeRate = editGridDom.wafGrid("getCell", rowid, "exchangeRate") || 0,
                    amountOriWithoutTax = editGridDom.wafGrid("getCell", rowid, "amountOriWithoutTax") || 0;
            var amountWithoutTax = _self.computeAmount(amountOriWithoutTax, convertMode, exchangeRate);
//			var amountWithoutTax = bizCollBillBase.ifConvertMode(convertMode) ?
//					_self.divideBigDecimal(amountOriWithoutTax,exchangeRate) : _self.multiplyBigDecimal(amountOriWithoutTax,exchangeRate);
            editGridDom.wafGrid("setCell", rowid, "amountWithoutTax", _self.setPrecision(amountWithoutTax));
        }
    	,
        refreshEntryAmount: function(rowid) {
            // 刷新指定行本位币金额,刷新合计
            var isWithTax = waf("#isWithTax").wafCheckbox("isChecked");
            var isEnableTax = true;
            var editGridDom = waf("#entries");
            var convertMode = editGridDom.wafGrid("getCell", rowid, "convertMode");
            var exchangeRate = editGridDom.wafGrid("getCell", rowid, "exchangeRate");
            var taxRate = editGridDom.wafGrid("getCell", rowid, "taxRate") || 0;
            var amountOri = editGridDom.wafGrid("getCell", rowid, "amountOri") || 0;
            if (isEnableTax && isWithTax) {
                var amountOriWithoutTax = _self.setPrecision(amountOri /( 1 + taxRate / 100));
                editGridDom.wafGrid("setCell", rowid, "amountOriWithoutTax", Number(amountOriWithoutTax));
                var tax = Number(amountOri) - Number(amountOriWithoutTax);
                editGridDom.wafGrid("setCell", rowid, "tax", _self.setPrecision(tax));
            } else if (isEnableTax && !isWithTax) {
                var amountOriWithoutTax = editGridDom.wafGrid("getCell", rowid, "amountOriWithoutTax") || 0;
                var tax = _self.setPrecision(amountOriWithoutTax * taxRate / 100);
                editGridDom.wafGrid("setCell", rowid, "tax", tax);
            } else if (!isEnableTax) {
                var amountOriWithoutTax = _self.setPrecision(amountOri /( 1 + taxRate / 100));
                editGridDom.wafGrid("setCell", rowid, "amountOriWithoutTax", Number(amountOriWithoutTax));
                var tax = Number(amountOri) - Number(amountOriWithoutTax);
                editGridDom.wafGrid("setCell", rowid, "tax", _self.setPrecision(tax));
            }
            var amountWithoutTax = _self.computeAmount(amountOriWithoutTax, convertMode, exchangeRate);
            var amount = _self.computeAmount(amountOri, convertMode, exchangeRate);
//			var amount = bizCollBillBase.ifConvertMode(convertMode) ? _self.divideBigDecimal(amountOri,exchangeRate)
//					: _self.multiplyBigDecimal(amountOri,exchangeRate);
            editGridDom.wafGrid("setCell", rowid, "amountWithoutTax", amountWithoutTax);
            editGridDom.wafGrid("setCell", rowid, "amount", amount);
        },
        updateCurrencyExRateAndAmount: function(currencyMap) {
            //根据最新的币别汇率Map刷新分录汇率和金额
            _public.updateEntryExRateAndAmount(currencyMap);
            //根据最新的币别汇率Map刷新收款分录汇率和金额
            bizCollBillBase.updateCollectionExRateAndAmount(currencyMap);
            bizCollBillBase.refreshCurrencyTotal("editGrid_collection", "amountOri",
                    "amount", "collectionCurrencyTotalText", _self._localeStr.AMOUNT_TO_PAY,_private.multiCurrencyId);
        },
        updateEntryExRateAndAmount: function(currencyMap) {
            //根据最新的币别汇率Map刷新分录汇率和金额
            if(!currencyMap || currencyMap.size()<1) return;
            var	entryDom = waf("#entries");
            var model = _self.getCurrentModel();
            var entryIds = entryDom.wafGrid("getDataIDs");

            //刷新分录汇率和金额
            for(var i = 0; i < entryIds.length; i++){
                var rowid = entryIds[i];
                var entryObj = entryDom.wafGrid("getRowRealData", rowid);
                var currency = entryObj.currencyType;
                if(currency == null || (model.currencyType && model.currencyType.id == currency.id)) continue;
                if(currencyMap.containsKey(currency.id)){
                    var exchangeRate = currencyMap.get(currency.id).exchangeRate;
                    var exchangeRatePrecision = currencyMap.get(currency.id).exchangeRatePrecision;
                    var convertMode = currencyMap.get(currency.id).convertMode;
                    if(exchangeRatePrecision != entryObj.exchangeRatePrecision){
                        entryDom.wafGrid("setCell", rowid, "exchangeRatePrecision", exchangeRatePrecision);
                    }
                    //择算方式都是设置的具体值，这里保持一致
                    if(!(convertMode.value == entryObj.convertMode ||
                            convertMode.value == (entryObj.convertMode && entryObj.convertMode.value))){
                        entryDom.wafGrid("setCell", rowid, "convertMode", convertMode.value);
                    }
                    entryDom.wafGrid("setCell", rowid, "exchangeRate", exchangeRate);
                    var rowObject = entryDom.wafGrid("getRowRealData", rowid);
                    var currencyrateValue = bizCollBillBase.currencyrateFormatter(null,null,rowObject,null,null);
                    //设置币别汇率格式
                    entryDom.wafGrid("setCell", rowid, "currencyrate", currencyrateValue);

                    // 更新本位币金额
                    _public.refreshEntryAmount(rowid);
                    // 刷新本位币不含税金额
                    _public.refreshEntryTaxAmount(rowid);
                }
            }
            //更新币别缓存
            _private.updateCurrencyMap();
            // 刷新合计金额
            _public.refreshTotalAmount("entries", "amount", "amount", true);
        },
        refreshCheckAmountForAll: function(rowid) {
            //刷新核销金额总入口方法
            var gridDom = waf("#entries"),
                    loanGridDom = waf("#loanCheckEntries"),
                    reqGridDom = waf("#reqCheckEntries");
            var loanIds = loanGridDom.wafGrid("getDataIDs");
            var reqIds = reqGridDom.wafGrid("getDataIDs");
            // 核销金额联动处理
            if (loanIds.length + reqIds.length == 1) {
                // 只有一条冲申请分录或者冲借款分录 && 非多币别核销时,进行金额联动
                _public.refreshCheckAmount(loanGridDom, loanIds[0], "loanAmount", "loanCheckCurrencyTotalText");
                _public.refreshCheckAmount(reqGridDom, reqIds[0], "reqAmount", "reqCheckCurrencyTotalText");
            }
        },
        refreshCheckAmount: function(gridDom, rowId, amountId, currencyTextId) {
            // 核销金额刷新
            if (!rowId) return;
            var totalAmount = waf("#amount").wafNumberField("getValue");
            bizCollBillBase.refreshCheckAmount(gridDom, rowId, amountId, currencyTextId, "amountOri", totalAmount);
        },
        refreshTotalAmount: function(gridId, amountId, amountProp, ifHideBudgetDesc) {
            // 刷新分录/冲销申请/冲销借款表格的金额合计
            waf("#" + gridId).wafGrid("editStop");
            waf("#" + gridId).wafGrid("calcFooterData");
//			var totalAmount = waf("#" + gridId).wafGrid("getFooterData")[amountProp];
            var totalAmount = waf("#" + gridId).wafGrid("getColValue", amountProp, false, "sum");
            waf("#" + amountId).wafNumberField("setValue", _self.setPrecision(totalAmount));
            if ("entries" == gridId) {
                //改变超预算说明
                //if (ifHideBudgetDesc) _private.changeBudgetDescription(false);
                // 刷新核销金额
                _public.refreshCheckAmountForAll();
                // 刷新多币别金额合计
                //bizCollBillBase.refreshCurrencyTotal(null,null,null,null,null, _public.multiCurrencyId);
                bizCollBillBase.refreshCurrencyTotal("entries", "amountOri", "amount", "currencyTotalText", _self._localeStr.REIMBURSEMENT_AMOUNT, _public.multiCurrencyId);
            }
            //刷新付现金额
            _public.refreshEncashedAmount();
        },
        refreshEncashedAmount: function() {
            bizCollBillBase.refreshEncashedAmount("amount", "amountOri", _public.currencyMap);

            var model = _self.getCurrentModel();
            //botp关联生成标志，关联生成时，自动计算多收款人金额
            var isfrombotprelation = model.isfrombotprelation;
            var isPullToAssBill = waf.getUrlParams(document.location.href).isPullToAssBill;
            if(_public.isLoaded == true || isfrombotprelation || isPullToAssBill){
                _public.initCollectionGrid();
            } else {//刷新收款分币别小计金额
                var collectionDom = waf("#editGrid_collection");
                collectionDom.wafGrid("calcFooterData");
                bizCollBillBase.refreshCurrencyTotal("editGrid_collection", "amountOri",
                        "amount", "collectionCurrencyTotalText", _self._localeStr.AMOUNT_TO_PAY, _public.multiCurrencyId);
            }
        }
	    ,
        initCollectionGrid: function(currencyObj) {
            var collectionEntry = waf("#editGrid_collection"),
                    rowIds = collectionEntry.wafGrid("getDataIDs");
            if (rowIds.length == 0) return;
            //取相同分录币别的汇率刷新收款分录币别汇率
            bizCollBillBase.refreshCollectionExRateByEntryRate();
            if (rowIds.length == 1) {
                //刷新收款金额
                _public.refreshCollectionAmount();
            }
//			else {
//				// 多条分录时，处理付现金额和收款总额误差，误差补到收款明细
//				bizCollBillBase.balanceEncashedAmountAndCollectionAmount();
//			}
        }
	    ,
        refreshCollectionAmount: function() {
            var encashedAmount = waf("#encashedAmount").wafNumberField("getValue");
            var amountOriSum =  _self.setPrecision(waf("#entries").wafGrid("getColValue", "amountOri", false, "sum"));
            //刷新收款金额
            bizCollBillBase.refreshCollectionAmount(encashedAmount, amountOriSum);
        }
		,
        isWithTaxChange:function(event,ui){
            _private.initIsWithTax(ui.checked);
        },
        setWidgetCSS:function(){
            $("#section_entries .sheader").append($("#bgTextColumn").css({"display":"inline-block","float":"none"}));

            $("#section_baseInfo .content").append($("#loanAccountColumn").css({"display":"inline-block","float":"none"}));

            $("#section_entries .sheader").append($("#isWithTaxColumn").css({
                    "position": "absolute",
                    "display":"inline-block",
                    "width":"300px",
                    "padding-left":"20px",
                    "float":"right",
                    "right":"450px"}));
            $("#section_collectionAccount .sheader").append($("#syncCollection").css({
                    "position": "absolute",
                    "right":"450px",
                    "display":"inline-block",
                    "background":"#FFF",
                    "width":"300px",
                    "text-indent":"20px",
                    "padding":"0",
                    "float":"right"}));

            waf("#bgDataText").hide();
        }
        ,
        appendEntryRow_EX:function(event,value){
            //非标准表格增行事件二开扩展
            _self._appendEntryRowActionEventHandler(event,value);
        },
        deleteEntryRow_EX:function(event,value){
            //非标准表格删行事件二开扩展
            _self._deleteEntryRowActionEventHandler(event,value);
        }
    });
        _self.getBillType = function() {return "BizAccountOutBill";};
        //_self.getEditUrl = function() {return "com.kingdee.eas.cp.bc.BizAccountOutBill_Edit";};

        _self.beforeSubmit = function() {
            bizCollBillBase.isFromWFSubmitWeb = true;
            var result = _self.fireEvent("submit");
            return result;
        };


        _self.subscribeEvent("judgeDataModifyActionEvent", _private.judgeDataModifyActionHandler, "overwrite");
        _self.subscribeEvent("setOldModelEvent", _private.setOldModelActionHandler, "overwrite");
        // 多收人分录-收款人F7
        waf.defineCustomeClass("celleditor.wafPayerF7", celleditor.defaultEditor, {
                show: function() {
            var btnId = this.opts.id + "btn";
            var btnOpts = {
                    tagClass: "g-icon-search",
                    style: "height: 24px; width: 20px;position: relative;margin-left: -25px;text-decoration: none;",
                    id: btnId
			};
            var rowId = waf("#editGrid_collection").wafGrid("getSelectedRow");
            var payerTypeValue = waf("#editGrid_collection").wafGrid("getCell", rowId,"payerType");
            var payerType = payerTypeValue.value || payerTypeValue;
            //应付集成（CP042=是），无论新增参数CP075为是或否 只支持F7选择。
            if(payerType != 10 && bizCollBillBase.isusedWithAP()){
                $(this.elem).wafText("option","disabled",true);
            }else if(payerType != 10 && !bizCollBillBase.isusedWithAP() && !bizCollBillBase.isPayerNameModifiable()){
                //应付集成（CP042=否），新增参数 CP075=否  只支持F7选择。
                $(this.elem).wafText("option","disabled",true);
            }
            var searchBtn = waf.createDOM("linkButton", btnOpts);
            //$(this.elem).css("padding-right", "20px");
            this.elem = $("<div style='width:100%;height:100%;border:none;display:inline-block;'></div>")
                    .append(this.elem).append(searchBtn);
            waf.initComponent("linkButton", btnOpts, searchBtn);
            waf(searchBtn).find("span").addClass("qsBtn");
            waf(searchBtn).wafLinkButton("option", "onclick", _private.showPayerF7);
            celleditor.defaultEditor.prototype.show.call(this);
        }
	});
        // 多收款人分录-银行账号F7
        waf.defineCustomeClass("celleditor.wafpayerAccountF7", celleditor.defaultEditor, {
                show: function() {
            //celleditor.defaultEditor.prototype.createEditor.call(this, value);
            var btnId = this.opts.id + "btn";
            var btnOpts = {
                    tagClass: "g-icon-search",
                    style: "height: 24px; width: 20px;position: relative;margin-left: -25px;text-decoration: none;",
                    id: btnId
			};
            var searchBtn = waf.createDOM("linkButton", btnOpts);
            //$(this.elem).css("padding-right", "20px");
            var model = _self.getCurrentModel(),
                    companyId = model.applierCompany && model.applierCompany.id;
            var editGridDom = waf("#editGrid_collection")
            var entryPayerAccountF7 = waf("#entryPayerAccountF7");
            entryPayerAccountF7.wafPromptStandard("option", "colModel", "");//清空colModel缓存
            var rowId = this.currentRow && this.currentRow.id;
            var payerNameEntryF7 = editGridDom.wafGrid("getCell", rowId,"payerNameEntryF7");
            var payerTypeValue = editGridDom.wafGrid("getCell", rowId,"payerType");
            var payerType = payerTypeValue.value || payerTypeValue;
            var companyFilter = waf.parseSql.getFilter("companyOrgUnit.id","=",companyId);
            var filer = "";
            if (payerType == "20" || payerType == "30") {
                if (payerNameEntryF7) {//有收款人F7
                    if (payerNameEntryF7.isInternalCompany != undefined) {
                        if (payerNameEntryF7.isInternalCompany == false) {//收款人是外部客户，初始化银行账号f7
                            if (payerType == "20") {//供应商
                                entryPayerAccountF7.wafPromptStandard("option", "title", _self._localeStr.SUPPLIER_BANK);
                                entryPayerAccountF7.wafPromptStandard("option", "defaultSearchItem", "bank+bankAccount+bankAddress");
                                entryPayerAccountF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7SupplierCompanyInfoBankQuery");
                                var supplierFilter =  waf.parseSql.getFilter("supplier.id","=",payerNameEntryF7.id);
                                entryPayerAccountF7.wafPromptStandard("option", "filteritem",waf.parseSql.mergeFilter([companyFilter,supplierFilter]," and"));
                            } else if (payerType == "30") {//客户
                                entryPayerAccountF7.wafPromptStandard("option", "title", _self._localeStr.CUSTOMER_BANK);
                                entryPayerAccountF7.wafPromptStandard("option", "defaultSearchItem", "bank+bankAccount+bankAddress");
                                entryPayerAccountF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7CustomerCompanyInfoBankQuery");
                                var customerFilter =  waf.parseSql.getFilter("customer.id","=",payerNameEntryF7.id);
                                entryPayerAccountF7.wafPromptStandard("option", "filteritem",waf.parseSql.mergeFilter([companyFilter,customerFilter]," and"));
                            }
                        }else if (payerNameEntryF7.isInternalCompany == true){//收款人是内部客户，初始化银行账号f7
                            entryPayerAccountF7.wafPromptStandard("option", "title", _self._localeStr.ACCOUNTBANK);
                            entryPayerAccountF7.wafPromptStandard("option", "defaultSearchItem", "name+number+bankAccountNumber");
                            entryPayerAccountF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.assistant.app.AccountBankQuery");
                            entryPayerAccountF7.wafPromptStandard("option", "filteritem",waf.parseSql.getFilter("company.id","=",payerNameEntryF7.internalCompany.id));
                        }
                        this.elem = $("<div style='width:100%;height:100%;border:none;display:inline-block;'></div>")
                                .append(this.elem).append(searchBtn);
                        waf.initComponent("linkButton", btnOpts, searchBtn);
                        waf(searchBtn).find("span").addClass("qsBtn");
                        waf(searchBtn).wafLinkButton("option", "onclick", _private.showPayerAccountF7);
                    }
                }else{//无收款人F7
                    if (payerType == "20") {//供应商
                        entryPayerAccountF7.wafPromptStandard("option", "title", _self._localeStr.SUPPLIER_BANK);
                        entryPayerAccountF7.wafPromptStandard("option", "defaultSearchItem", "bank+bankAccount+bankAddress");
                        entryPayerAccountF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7SupplierCompanyInfoBankQuery");
                        entryPayerAccountF7.wafPromptStandard("option", "filteritem",companyFilter);
                    } else if (payerType == "30") {//客户
                        entryPayerAccountF7.wafPromptStandard("option", "title", _self._localeStr.CUSTOMER_BANK);
                        entryPayerAccountF7.wafPromptStandard("option", "defaultSearchItem", "bank+bankAccount+bankAddress");
                        entryPayerAccountF7.wafPromptStandard("option", "query", "com.kingdee.eas.basedata.master.cssp.app.F7CustomerCompanyInfoBankQuery");
                        entryPayerAccountF7.wafPromptStandard("option", "filteritem",companyFilter);
                    }
                    this.elem = $("<div style='width:100%;height:100%;border:none;display:inline-block;'></div>")
                            .append(this.elem).append(searchBtn);
                    waf.initComponent("linkButton", btnOpts, searchBtn);
                    waf(searchBtn).find("span").addClass("qsBtn");
                    waf(searchBtn).wafLinkButton("option", "onclick", _private.showPayerAccountF7);
                }
            }
            celleditor.defaultEditor.prototype.show.call(this);
        }
	});
        // 多收款人分录-收款银行F7
        waf.defineCustomeClass("celleditor.wafPayerBankF7", celleditor.defaultEditor, {
                show: function() {
            //celleditor.defaultEditor.prototype.createEditor.call(this, value);
            var btnId = this.opts.id + "btn";
            var btnOpts = {
                    tagClass: "g-icon-search",
                    style: "height: 24px; width: 20px;position: relative;margin-left: -25px;text-decoration: none;",
                    id: btnId
			};
            var searchBtn = waf.createDOM("linkButton", btnOpts);
            //$(this.elem).css("padding-right", "20px");
            var initData = bizCollBillBase.getModelInitData();
            if (String(initData.CP053) == 'false') {
                $(this.elem).wafText("option","readonly",true);
            }
            this.elem = $("<div style='width:100%;height:100%;border:none;display:inline-block;'></div>")
                    .append(this.elem).append(searchBtn);
            waf.initComponent("linkButton", btnOpts, searchBtn);
            waf(searchBtn).find("span").addClass("qsBtn");
            waf(searchBtn).wafLinkButton("option", "onclick", _private.showPayerBankF7);
            celleditor.defaultEditor.prototype.show.call(this);
        }
	});
        // 费用承担部门—公司信息
        waf.defineCustomeClass("celleditor.companyCostEditor", celleditor.defaultEditor, {
                show: function() {
//			celleditor.defaultEditor.prototype.show.call(this);
            var entryDom = waf("#entries");
            var rowid = entryDom.wafGrid("getSelectedRow");
            var company = entryDom.wafGrid("getCell", rowid, "company");
            var costCenter = entryDom.wafGrid("getCell", rowid, "costCenter");
            _public.needChangeEvent = false;
            waf("#entryCostCenter").wafPromptBox("setValue", costCenter);
            waf("#entryCompany").wafPromptBox("setValue", company);
            _public.needChangeEvent = true;
            var columnWidth = this.cell.width();
            var companyCostColumn = waf("#companyCostColumn");
            companyCostColumn.attr("infodiv","true");
            var table = this.table;
            companyCostColumn.width(columnWidth);
            $(companyCostColumn).css({
                    "background":"#FFFFFF",
                    "box-shadow": "0 0 13px 0 rgba(0,0,0,0.20)",
                    "border-radius": "2px",
                    "border":"none",
                    "width":"270px",
                    "min-width":"270px"
			})
            companyCostColumn.show();
            companyCostColumn.position($.extend({of: this.cell}, {my: "left top", at: "left top"}));
        },
        clear: function() {
            //waf("#companyCostColumn").hide();
            celleditor.defaultEditor.prototype.clear.call(this);
        }
	});
        // 币别—汇率
        waf.defineCustomeClass("celleditor.currencyRateEditor", celleditor.defaultEditor, {
                show: function() {
            //celleditor.defaultEditor.prototype.show.call(this);
            var entryDom = waf("#entries");
            var rowid = entryDom.wafGrid("getSelectedRow");
            var currencyType = entryDom.wafGrid("getCell", rowid, "currencyType");
            var exchangeRate = entryDom.wafGrid("getCell", rowid, "exchangeRate");
            var exchangeRatePrecision = entryDom.wafGrid("getCell", rowid, "exchangeRatePrecision");
            waf("#entryCurrencyType").wafPromptBox("setValue", currencyType, false);
            //设置汇率前，先设置汇率字段的精度
            waf("#entryExchangeRate").wafNumberField("option","decimalPrecision", exchangeRatePrecision);
            waf("#entryExchangeRate").wafNumberField("setValue", exchangeRate, false);
            //本位币 汇率不允许修改
            bizCollBillBase.initExchangeRateCss(currencyType);

            var columnWidth = this.cell.width();
            var currencyRateColumn = waf("#currencyRateColumn");
            currencyRateColumn.attr("infodiv","true");
            var table = this.table;
            currencyRateColumn.width(columnWidth);
            currencyRateColumn.show();
            currencyRateColumn.position($.extend({of: this.cell}, {my: "left top", at: "left top"}));
            $(currencyRateColumn).css({
                    "background":"#FFFFFF",
                    "box-shadow": "0 0 13px 0 rgba(0,0,0,0.20)",
                    "border-radius": "2px",
                    "border":"none",
                    "width":"270px",
                    "min-width":"270px"
			})
        },
        clear: function() {
            //waf("#currencyRateColumn").hide();
            celleditor.defaultEditor.prototype.clear.call(this);
        }
	});

        /**************************开发区域结束**********************************************/
        return _public;
    });

}
