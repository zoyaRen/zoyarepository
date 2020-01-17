$.ajaxSetup({
    dataType: "json",
    contentType: "application/json;charset=UTF-8",
    cache: false
});

//选择多条记录
function getSelectedRows() {
    //返回所有选择的行，当没有选择的记录时，返回一个空数组
    var rows = $("#table").bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert('请选择一条记录');
        return;
    }
    return rows;
}

//选择一条记录
function getSelectedRow() {
    //返回所有选择的行，当没有选择的记录时，返回一个空数组
    var rows = $("#table").bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert('请选择一条记录');
        return;
    }

    if(rows.length > 1){
        alert("只能选择一条记录");
        return ;
    }
    return rows[0];
}

$(function () {
    $("#table").bootstrapTable({
        type:"GET",
        url:'/title',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        pagesize:10,
        pageList:[5,10,15],
        toolbar: '#toolbar',
        striped : true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {checkbox:true},
            { title: '编号', field: 'id',sortable:true},
            { title:'标题',field:'title'},
            { title: '副标题', field: 'subTitle'},
            { title: '品牌', field: 'bname'},
            { title: '分类名', field: 'cname'},
            { title: '是否可售', field: 'saleable', formatter: function(value){
                    if(value === 1){
                        return '<span class="label label-primary">可售</span>';
                    }
                    else{
                        return '<span class="label label-warning">不可售</span>';
                    }
                }},
            { title: '是否有效', field: 'valid', formatter: function(value){
                    if(value === 1){
                        return '<span class="label label-primary">有效</span>';
                    }
                    else{
                        return '<span class="label label-warning">无效</span>';
                    }
                }},
            { title: '创建时间', field: 'createTime'},
            { title: '最新更新时间', field: 'lastUpdateTime'}
        ]
    });
});

var vm = new Vue({
    el:'#app',
    data:{
        sups:{},
        sup:{},
        showList:true,
        title:""
    },
    methods:{
        del:function () {
            var id ='id';
            var rows = getSelectedRows();
            var ids = new Array();
            $.each(rows, function(i, row) {
                ids[i] = row[id];//得到选择的这一行的id
            });
            if(ids.length!=0){
                if(!confirm("确定删除所选记录？")){
                    return;
                }
                $.ajax({
                    url:"/",
                    data:JSON.stringify(ids),
                    type:"DELETE",
                    success:function (r) {
                        alert(r.code==0?"error":"success");
                        vm.refresh();
                    },
                    error:function () {
                        alert("connect failed");
                    }
                })
            }
        },
        update:function () {
            var id ='id';
            var rows = getSelectedRow();
            var rid=rows[id];
            if(rid==null){
                return;
            };
            //更新前，需要根据id查到这条记录并回填
            $.ajax({
                url:"/id?id="+rid,
                type:"get",
                success:function (r) {
                    vm.sup=r.resultList[0];
                    vm.title="更新";
                    vm.showList=false;
                 }
            })
        },
        add:function () {
            vm.title="新增";
            vm.showList=false;
        },
        modify:function(){
            var type = vm.sup.id==null?"POST":"PUT";
            $.ajax({
                url:"/",
                type:type,
                data:JSON.stringify(vm.sup),
                success:function (r) {
                    this.sup={};
                    alert(r.code==1?"success":"error");
                    vm.sup={};
                    vm.showList=true;
                    vm.refresh();
                },
                error:function () {
                    alert("connect failed");
                }
            })
        },
        refresh:function () {
            vm.showList=true;
            $("#table").bootstrapTable("refresh");
        }
    }
})

