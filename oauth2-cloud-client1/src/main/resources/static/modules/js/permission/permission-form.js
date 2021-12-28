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
            enable: false
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

                console.log(treeNode.id, treeNode.name)
                if(treeNode.id == $("#id").val()) {
                    console.log(treeId)
                    layer.tips('自已不能作为父资源', '#'+treeId, {time: 1000})
                    return;
                }


                // $('#parentId').val(treeNode.id);
                // $('#parentName').val(treeNode.name);
                parentPermission(treeNode.id, treeNode.name);
            }
        }
    };


    $.post(contextPath + "permission/list", function (data) {
        var permissionTree = $.fn.zTree.init($("#permissionTree"), menuSetting, data.data);
        var parentIdVal = $("#parentId").val();
        console.log('parentIdVal', parentIdVal);

        if(parentIdVal !== null && parentIdVal !== '' && parentIdVal !== undefined && parentIdVal != 0) {

            var nodes = permissionTree.getNodesByParam("id", parentIdVal, null);
            // console.log(nodes[0].name);
            $("#parentName").val(nodes[0].name);
        }
    })
}


function parentPermission(parentId, parentName) {
    if(parentId == null || parentName == null) {
        parentId = 0;
        parentName = '根菜单';
    }
    $('#parentId').val(parentId);
    $('#parentName').val(parentName);
}
