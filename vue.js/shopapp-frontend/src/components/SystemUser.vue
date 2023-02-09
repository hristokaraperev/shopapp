<template>
    <div class="container">
        <h1 class="text-center"> Users List </h1>
        <table class="table table-striped">
            <thead>
                <th> ID </th>
                <th> Username </th>
                <th> Password </th>
                <th> Is Employee </th>
            </thead>
            <tbody>
                <tr v-for="user in users" v-bind:key="user.id">
                    <td> {{user.id}} </td>
                    <td> {{user.username}} </td>
                    <td> {{user.password}} </td>
                    <td> {{user.isEmployee}} </td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
import SystemUserService from '../services/SystemUserService'

    export default {
        name: 'SystemUser',
        data() {
            return {
                users : []
            }
        },
        methods: {
            getUsers() {
                SystemUserService.
                getUsers().
                then((response) => {
                    this.users = response.data;
                });
            }
        },
        created() {
            this.getUsers();
        }
    }
</script>

<style>

</style>


