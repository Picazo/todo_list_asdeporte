# TODO LIST APP
Aplicación para agregar/modificar/eliminar tareas pendientes.

Se agregao un pequeño plus en el cual el usuario tambien puede agregar categorias.

Estructura de la aplicació:

La aplicacion fue hecha con Clean Architecture y MVVM , donde el ViewModel se centra en la presentacion a la vista, otras tecnologias utilizadas fueron:

Hilt , usada para la injeccion de dependecias para separar de manera modular.
Room , para guardar la base de datos local.

La aplicacion consta de las siguientes secciones:

Bienvenida:

<img width="272" alt="Captura de pantalla 2023-05-17 a la(s) 1 42 07" src="https://github.com/Picazo/todo_list_asdeporte/assets/17649817/beff1627-a375-4f96-9f6c-92e13ad40b3f">

Se utiliza esta pantalla para dar una introducción al usuario sobre lo que espera como funcionalidades.

Home:

<img width="265" alt="Captura de pantalla 2023-05-17 a la(s) 1 42 25" src="https://github.com/Picazo/todo_list_asdeporte/assets/17649817/6e7e930e-5a49-4fe9-8425-697a5c8d2d0c">


La pantalla de Home muestra las categorias previamente almacenadas por el usuario, en caso contrario que sea la primera interacción con la aplicación , se muestra un estado para poder agregar una tarea.

Flujo para agregar tarea:

<img width="264" alt="Captura de pantalla 2023-05-17 a la(s) 1 42 29" src="https://github.com/Picazo/todo_list_asdeporte/assets/17649817/2c138bc9-d3fe-49ad-ae0b-6b1ed9541caa">

En este se piden datos en especifico que puede contener la tarea

Agregar categoria:

<img width="265" alt="Captura de pantalla 2023-05-17 a la(s) 1 42 46" src="https://github.com/Picazo/todo_list_asdeporte/assets/17649817/b74f2f7b-c83d-41cf-a61d-24e20143dc1e">


Posterior a agregar la tarea, se pide a que categoria asignarla, para asi , poder insertarla en la BD

Detalle de tarea:

<img width="262" alt="Captura de pantalla 2023-05-17 a la(s) 1 43 22" src="https://github.com/Picazo/todo_list_asdeporte/assets/17649817/b82da226-82aa-4621-ba2a-c4c9fe4f209b">


Edicion de tarea:

<img width="267" alt="Captura de pantalla 2023-05-17 a la(s) 1 43 27" src="https://github.com/Picazo/todo_list_asdeporte/assets/17649817/8c520705-776d-431d-93c6-5b3317ebedf6">





