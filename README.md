# CRM - Arquitectura Hexagonal


<div align="center">
<img src="./imagenes/arquitecturaHexagonal.png" >
</div>

## Adaptadores
### primarios (entrada)
* Controladores
* Colas de mesages (Rabbit, Kafka.... etc) - Consumidores
* APIs Externas
* Servicios de correo
### secundarios (salida)
* Repositorios (Base de datos)
* Colas de mensages (Rabbit, Kafka.... etc) - Publicadores

## Puertos
* Interfaces (Servicios)

## Casos de uso
* implementaciones de servicios
## Entidades
* Modelos de datos (Tabla de bases de datos)

## Configuraciones
## Dominio
* modelo
* puertos
* casos de uso

## Logica de negocios del proyecto
El proyecto consiste en un CRM que simula un concesionario de vehiculos, mediante el CRM  los usuarios con rol de Ventas son los encargados de la administración  de todos los clientes que estan registrados en el CRM. Cada usuario gestiona sus respectivos clientes, un cliente no puede estar asociado a dos usuarios del CRM. Los clientes pueden mostrar interes por varios vehiculos, los mismos que estan registrados en el CRM, y el usuario es el encargado de registrar el numero de vehiculos en los cuales esta interesado un cliente y llevar su seguimiento hasta la compra del vehiculo.
