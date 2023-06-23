package com.dim.servicio.interfaz;

import java.util.Collection;

public interface ServicioCrud<E>{

    /**
     * Guarda un objeto en la base de datos
     * @param entidad       objeto a ser guardado
     * @return              objeto guardado con su id asignado
     */
    E guardar(final E entidad);

    /**
     * Busca el objeto que posee él {@code id} dado y lo actualiza
     * con los datos que trae el objeto {@code entidad}
     * @param id            id del objeto que se desea actualizar
     * @param entidad       objeto que trae los nuevos valores
     * @return              el objeto con los campos actualizados
     */
    E actualizar(final Long id, final E entidad);

    /**
     * Busca el objeto por su {@code id}
     * @param id            id del objeto que se desea buscar
     * @return              el objeto encotrado en la base de datos
     * @throws              org.springframework.data.crossstore.ChangeSetPersister.NotFoundException si no se encuentra ningún objeto
     */
    E buscarPorId(final Long id);

    /**
     * @return              colección de objetos que se encuentren en la base de datos
     */
    Collection<E> buscarTodos();

    /**
     * @return              la cantidad de objetos guardados en la base de datos
     */
    Long cantidad();

    /**
     * Elimina el objeto que posee {@code id} dado
     * @param id            id del objeto que se desea eliminar
     */
    void eliminar(final Long id);
}
