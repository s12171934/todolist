<template>
  <li
    @mousedown.left="$emit('update', todo.id)"
    @contextmenu.prevent="$emit('delete', todo.id)"
    :key="todo"
    :id="todo.id"
    :class="todo.done ? 'completed' : ''"
  >
    {{ todo.content }}
  </li>
</template>

<script>
export default {
  props: {
    todo: Object,
  },
};
</script>

<style></style>
