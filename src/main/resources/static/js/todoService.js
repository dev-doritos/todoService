'use strict';

const status    = document.querySelector('select[name=status]');
const deadline  = document.querySelector('input[name=deadline]');
const todo      = document.querySelector('textarea[name=todo]');

async function fnRegist() {

    if (status.value === '') {
        alert('상태를 선택해주세요.');
        return false;
    }

    if (deadline.value === '') {
        deadline.showPicker();
        return false;
    }

    if (todo.value === '') {
        todo.focus();
        alert('Todo 를 입력해주세요.');
        return false;
    }

    const updateUrl = './';
    const response = await fetch(updateUrl, {
        method : 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body : JSON.stringify({
            todo : todo.value,
            status : status.value,
            deadline : deadline.value,
        }),
    });

    const data = await response.json();

    if (data.res_cd !== '0000') {
        alert(data.res_msg);
        return false;
    }

    alert('등록되었습니다.');

    const res_data = JSON.parse(data.res_data);
    location.href = `./${res_data.id}`;
}

async function fnUpdate() {

    if (status.value === '') {
        alert('상태를 선택해주세요.');
        return false;
    }

    if (deadline.value === '') {
        deadline.showPicker();
        return false;
    }

    if (todo.value === '') {
        todo.focus();
        alert('Todo를 입력해주세요.');
        return false;
    }

    const updateUrl = `./${Todo.id}`;
    const response = await fetch(updateUrl, {
        method : 'put',
        headers: {
            'Content-Type': 'application/json',
        },
        body : JSON.stringify({
            id : Todo.id,
            todo : todo.value,
            status : status.value,
            deadline : deadline.value,
            member : Todo.member
        }),
    });

    const data = await response.json();

    if (data.res_cd !== '0000') {
        alert(data.res_msg);
        return false;
    }

    alert('수정되었습니다.');
}