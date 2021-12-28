// permissionTree
$(function () {
    loadPermissionTree();
});



function loadPermissionTree() {

    var menuSetting = {
        view: {
            showLine: true
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: 0
            },
            key: {
                name: "name",
                title: "name"
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {

                event.preventDefault();

            }
        }
    };


    $.post(contextPath + "permission/list", function (data) {
        var permissionTree = $.fn.zTree.init($("#permissionTree"), menuSetting, data.data);


        var id = $("#id").val();
        if(id !== '' && id !== null && id !==undefined) {

            var perIds = JSON.parse($("#perIds").val());

            for(var i=0; i< perIds.length; i++) {
                var nodes = permissionTree.getNodesByParam("id", perIds[i], null);

                permissionTree.checkNode(nodes[0], true, false);

                permissionTree.expandNode(nodes[0], true, false);
            }
        }
    })
}


$("#form").submit(function () {

    var treeObj = $.fn.zTree.getZTreeObj("permissionTree");

    var nodes = treeObj.getCheckedNodes(true);
    var perIds = [];
    for(var i=0; i<nodes.length; i++) {
        perIds.push(nodes[i].id);
    }
    $("#perIds").val(perIds);

    return true;
});


