'use client';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import React, { useEffect, useRef, useState } from 'react';
import { createSpeciality, deleteSpeciality, getAllSpecialities, updateSpeciality } from '../../services/speciality';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { Dropdown } from 'primereact/dropdown';
import { HttpStatusCode } from 'axios';
import { Toast } from 'primereact/toast';

const SpecialitiesPage = () => {
    const [specialities, setSpecialities] = useState([]);
    const [dialogVisible, setDialogVisible] = useState(false);
    const [dialogTitle, setDialogTitle] = useState('');
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');

    const statues = [
        { name: 'Active', code: true },
        { name: 'Inactive', code: false }
    ];

    const toast = useRef(null);

    const fetchData = async () => {
        const result = await getAllSpecialities();
        setSpecialities(result.data.data);
    };

    useEffect(() => {
        fetchData();
    }, []);

    const activeBodyTemplate = (rowData) => {
        return <span
            className={`customer-badge status-${rowData.active ? 'qualified' : 'unqualified'}`}>{rowData.active ? 'Active' : 'Inactive'}</span>;
    };

    const dialogFooter = (
        <div>
            <Button label="Cancel" severity="danger" icon="pi pi-times" onClick={() => closeDialog()}
                    outlined />
            <Button label="Save" icon="pi pi-check" outlined
                    onClick={async () => (id) ? await update() : await create()} />
        </div>
    );

    const create = async () => {
        const formData = getFormData();

        try {
            const result = await createSpeciality(formData);
            if (result.status === HttpStatusCode.Created) {
                toast.current.show({
                    severity: 'success',
                    summary: 'Success',
                    detail: `Created successfully`,
                    life: 2000
                });

                await fetchData();
            }
        } catch (error) {
            console.log(error);

            toast.current.show({
                severity: 'error',
                summary: 'Failed',
                detail: `Create failed`,
                life: 2000
            });
        } finally {
            closeDialog();
        }
    };

    const update = async () => {
        const formData = getFormData();

        try {
            const result = await updateSpeciality(id, formData);
            if (result.status === HttpStatusCode.Ok) {
                toast.current.show({
                    severity: 'success',
                    summary: 'Success',
                    detail: `Updated successfully`,
                    life: 2000
                });

                await fetchData();
            }
        } catch (error) {
            console.log(error);

            toast.current.show({
                severity: 'error',
                summary: 'Failed',
                detail: `Updated failed`,
                life: 2000
            });
        } finally {
            closeDialog();
        }
    };

    const deleteItem = async (rowData) => {
        console.log(rowData);
        try {
            const result = await deleteSpeciality(rowData.id);
            if (result.status === HttpStatusCode.NoContent) {
                toast.current.show({
                    severity: 'success',
                    summary: 'Success',
                    detail: `Deleted successfully`,
                    life: 2000
                });

                setSpecialities(specialities.filter(speciality => speciality.id !== rowData.id));
            }
        } catch (error) {
            console.log(error);

            toast.current.show({
                severity: 'error',
                summary: 'Failed',
                detail: `Delete failed`,
                life: 2000
            });
        }
    };

    const getFormData = () => {
        return {
            name: name,
            description: description,
            active: status
        };
    };

    const closeDialog = () => {
        setDialogVisible(false);
        clearForm();
    };

    const clearForm = () => {
        setId('');
        setName('');
        setDescription('');
        setStatus('');
    };

    const openDialog = (title, rowData) => {
        setDialogTitle(title);
        setDialogVisible(true);
        if (rowData) {
            setId(rowData.id);
            setName(rowData.name);
            setDescription(rowData.description);
            setStatus(rowData.active);
        }
    };

    return (
        <>
            <Toast ref={toast} />
            <Dialog header={dialogTitle}
                    footer={dialogFooter}
                    visible={dialogVisible}
                    style={{ width: '25vw' }}
                    onHide={() => closeDialog()}>
                <div className="field">
                    <label htmlFor="name" className="block font-medium mb-2">
                        Name
                    </label>
                    <InputText id="name"
                               type="text"
                               value={name}
                               onChange={(e) => setName(e.target.value)}
                               className="w-full md:w-30rem mb-3" />
                </div>
                <div className="field">
                    <label htmlFor="description" className="block font-medium mb-2">
                        Description
                    </label>
                    <InputTextarea id="description"
                                   type="text"
                                   value={description}
                                   onChange={(e) => setDescription(e.target.value)}
                                   rows="3"
                                   className="w-full md:w-30rem mb-3" />
                </div>
                <div className="field">
                    <label htmlFor="status" className="block font-medium mb-2">
                        Status
                    </label>
                    <Dropdown id="status"
                              value={status}
                              onChange={(e) => setStatus(e.target.value)}
                              options={statues}
                              optionLabel="name"
                              optionValue="code"
                              placeholder="Select"
                              className="w-full md:w-30rem mb-3" />
                </div>
            </Dialog>
            <div className="grid">
                <div className="col-12">
                    <div className="card">
                        <div className="flex justify-content-between mb-3">
                            <h5>All Specialities</h5>
                            <Button label="Create" icon="pi pi-plus" size="small"
                                    onClick={() => openDialog('Create')} />
                        </div>
                        <DataTable value={specialities}>
                            <Column field="index" header="#"
                                    body={(rowData, rowIndex) => rowIndex.rowIndex + 1}></Column>
                            <Column field="name" header="Name"></Column>
                            <Column field="description" header="Description"></Column>
                            <Column field="active" header="Status" dataType="boolean"
                                    style={{ minWidth: '8rem' }} body={activeBodyTemplate} />
                            <Column header="Action" body={(rowData) => (
                                <div>
                                    <Button icon="pi pi-pencil" severity="primary" rounded text
                                            onClick={() => openDialog('Update', rowData)} />
                                    <Button icon="pi pi-trash" severity="danger" rounded text
                                            onClick={async () => await deleteItem(rowData)} />
                                </div>
                            )}></Column>
                        </DataTable>
                    </div>
                </div>
            </div>
        </>
    );
};

export default SpecialitiesPage;
