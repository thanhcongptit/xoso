function createNews(){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/post/ajax/add-product.jsp",
        data: {
            action : 'create_post'
        },
        success: function(result){
            window.location.href = contextPath+"/admin/post/add/index.jsp";
        }
    });
}

function addProduct(param){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/post/ajax/add-product.jsp?" + param,
        data: {
            action : 'addProduct'
        },
        success: function(result){
            rendTable();
        }
    });
}

function removeProduct(videoId){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/post/ajax/add-product.jsp",
        data: {
            action : 'removeProduct',
            videoId: videoId
        },
        success: function(result){
            $('#tr'+ videoId).remove();
        }
    });
}

function editNew(idNews,i){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/post/ajax/add-product.jsp",
        data: {
            action : 'edit_post',
            post_id : idNews
        },
        success: function(result){
            $('#editForm'+i).submit();
        }
    });
}